package example;

import org.concordion.api.*;
import org.concordion.api.extension.Extension;
import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.ProxyConfig;
import org.concordion.cubano.driver.concordion.ExceptionHtmlCaptureExtension;
import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.framework.ConcordionBrowserFixture;
import org.concordion.cubano.framework.resource.CloseListener;
import org.concordion.cubano.framework.resource.ResourceScope;
import org.concordion.cubano.template.AppConfig;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.net.Proxy;

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

    @Override
    public void registerCloseableResource(Closeable resource, ResourceScope scope) {
        CloseListener listener = new CloseListener() {

            @Override
            public void beforeClosing(Closeable resource) {
                // Prevent any further cards being added to the storyboard
                getStoryboard().setAcceptCards(false);
                reportLogger.step("Clean up data for " + resource);
            }

            @Override
            public void afterClosing(Closeable resource) {
                // Shouldn't need to do this, but this event getting triggered AFTER the Storyboard's afterExample event listener.
                getStoryboard().setAcceptCards(true);
            }
        };
        super.registerCloseableResource(resource, scope, listener);
    }
}
