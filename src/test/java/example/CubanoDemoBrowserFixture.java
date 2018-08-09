package example;

import org.concordion.api.*;
import org.concordion.api.extension.Extension;
import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.ProxyConfig;
import org.concordion.cubano.data.DataCleanupHelper;
import org.concordion.cubano.driver.concordion.ExceptionHtmlCaptureExtension;
import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.framework.ConcordionBrowserFixture;
import org.concordion.cubano.template.AppConfig;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Customises the test specification and provides some helper methods
 * so the tests can access the storyboard, browser, etc.
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoBrowserFixture extends ConcordionBrowserFixture {
    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    @Extension
    private final ExceptionHtmlCaptureExtension htmlCapture = new ExceptionHtmlCaptureExtension(getStoryboard(), getBrowser());

    @ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<DataCleanupHelper> dataHolder = new ScopedObjectHolder<DataCleanupHelper>() {
        @Override
        public DataCleanupHelper create() {
            return new DataCleanupHelper();
        }
    };

    static {
        ProxyConfig proxyConfig = Config.getInstance().getProxyConfig();
        AppConfig config = AppConfig.getInstance();
        config.logSettings();

        // Set the proxy rules for all rest requests made during the test run
        HttpEasy.withDefaults().allowAllHosts().trustAllCertificates().baseUrl(config.getBaseUrl());

        if (proxyConfig.isProxyRequired()) {
            HttpEasy.withDefaults()
                    .proxy(new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress(proxyConfig.getProxyHost(), proxyConfig.getProxyPort())))
                    .bypassProxyForLocalAddresses(true);

            if (!proxyConfig.getProxyUsername().isEmpty() && !proxyConfig.getProxyPassword().isEmpty()) {
                HttpEasy.withDefaults().proxyAuth(proxyConfig.getProxyUsername(), proxyConfig.getProxyPassword());
            }
        }
    }

    /**
     * Gets the logger for the current test.
     *
     * @return Logger
     */
    public ReportLogger getLogger() {
        return logger;
    }

    @BeforeSpecification
    private final void beforeSpecification() {
        logger.info("Initialising the acceptance test class {} on thread {}", getRelativeTestClassName(), Thread.currentThread().getName());
    }

    @BeforeExample
    private final void beforeExample(@ExampleName String exampleName) {
        logger.info("Starting example {} for test fixture {}", exampleName, getRelativeTestClassName());
    }

    private String getRelativeTestClassName() {
        // This is the name that can be given to the RunSingleTest job in Jenkins
        return this.getClass().getName().replace(CubanoDemoBrowserFixture.class.getPackage().getName() + ".", "");
    }

    @AfterExample
    private final void afterExample() {

        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            // Prevent any further cards being added to the storyboard
            getStoryboard().setAcceptCards(false);
            logger.step("Clean up data for " + dataHolder.get());
            dataHolder.get().cleanup();
            // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
            getStoryboard().setAcceptCards(true);
        }

    }

    @AfterSpecification
    private final void afterSpecification() {
        logger.info("Tearing down the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());

        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            // Prevent any further cards being added to the storyboard
            getStoryboard().setAcceptCards(false);
            logger.step("Clean up data for '%s'", dataHolder.get());

            dataHolder.get().cleanup();
            // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
            getStoryboard().setAcceptCards(true);
        }
    }

    public DataCleanupHelper getCleanupService() {
        return dataHolder.get();
    }
}
