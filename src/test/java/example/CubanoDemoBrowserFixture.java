package example;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.concordion.api.AfterExample;
import org.concordion.api.AfterSpecification;
import org.concordion.api.ConcordionResources;
import org.concordion.api.ConcordionScoped;
import org.concordion.api.FailFast;
import org.concordion.api.Scope;
import org.concordion.api.ScopedObjectHolder;
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

/**
 * Customises the test specification and provides some helper methods
 * so the tests can access the storyboard, browser, etc.
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoBrowserFixture extends ConcordionBrowserFixture {
    private final ReportLogger reportLogger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

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

    /** Override the default reportLogger. **/
    public CubanoDemoBrowserFixture() {
        super.withFixtureListener(new CubanoDemoFixtureLogger());
    }

    @AfterExample
    private final void afterExample() {

        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            // Prevent any further cards being added to the storyboard
            getStoryboard().setAcceptCards(false);
            reportLogger.step("Clean up data for " + dataHolder.get());
            dataHolder.get().cleanup();
            // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
            getStoryboard().setAcceptCards(true);
        }

    }

    @AfterSpecification
    private final void afterSpecification() {
        // Cleanup any data registered with data cleanup service
        if (dataHolder.isCreated() && dataHolder.get().hasCleanupItems()) {
            // Prevent any further cards being added to the storyboard
            getStoryboard().setAcceptCards(false);
            reportLogger.step("Clean up data for '%s'", dataHolder.get());

            dataHolder.get().cleanup();
            // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
            getStoryboard().setAcceptCards(true);
        }
    }

    public DataCleanupHelper getCleanupService() {
        return dataHolder.get();
    }
}
