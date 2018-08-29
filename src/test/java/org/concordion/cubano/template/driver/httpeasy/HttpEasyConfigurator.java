package org.concordion.cubano.template.driver.httpeasy;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.ProxyConfig;
import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.template.AppConfig;

public class HttpEasyConfigurator {

    private HttpEasyConfigurator() {
    }

    public static void applyDefaultSettings() {
        ProxyConfig proxyConfig = Config.getInstance().getProxyConfig();
        AppConfig config = AppConfig.getInstance();

        // Automatically trust all hosts and certificates
        HttpEasy.withDefaults().trustAllCertificates(true).trustAllHosts(true).baseUrl(config.getBaseUrl());

        // Set the proxy rules for all rest requests made during the test run
        if (proxyConfig.isProxyRequired()) {

            HttpEasy.withDefaults()
                    .proxy(new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress(proxyConfig.getProxyHost(), proxyConfig.getProxyPort())))
                    .bypassProxy(true);

            if (!proxyConfig.getProxyUsername().isEmpty() && !proxyConfig.getProxyPassword().isEmpty()) {
                HttpEasy.withDefaults().proxyAuth(proxyConfig.getProxyUsername(), proxyConfig.getProxyPassword());
            }

        }
    }
}
