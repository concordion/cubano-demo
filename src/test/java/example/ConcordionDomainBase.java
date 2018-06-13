package example;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.concordion.api.ConcordionResources;
import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.api.option.ConcordionOptions;
import org.concordion.api.option.MarkdownExtensions;
import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.ProxyConfig;
import org.concordion.cubano.driver.concordion.ExpectedToFailInfoExtension;
import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.framework.ConcordionBase;
import org.concordion.cubano.template.AppConfig;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.TimestampFormatterExtension;
import org.concordion.ext.runtotals.RunTotalsExtension;

/**
 * Sets up any Concordion extensions or other items that must be shared between
 * index and test fixtures.
 * 
 * NOTE: Test can be run from a Fixture or an Index, any global (@...Suite)
 * methods must be in this class to ensure the are executed from whichever class
 * initiates the test run.
 */
@ConcordionResources("/customConcordion.css")
@Extensions({ TimestampFormatterExtension.class, RunTotalsExtension.class, ExpectedToFailInfoExtension.class })
@ConcordionOptions(markdownExtensions = { MarkdownExtensions.HARDWRAPS, MarkdownExtensions.AUTOLINKS })
public abstract class ConcordionDomainBase extends ConcordionBase {

    @Extension
    private final StoryboardExtension storyboard = new StoryboardExtension();

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
     * @return A reference to the Storyboard extension.
     */
    public StoryboardExtension getStoryboard() {
        return storyboard;
    }
}
