package org.concordion.cubano.template;

import org.concordion.cubano.config.Config;
import org.concordion.cubano.config.PropertyLoader;
import org.concordion.cubano.driver.web.config.WebDriverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
    private final PropertyLoader propertyLoader;

    private String baseUrl;
    private String searchUrl;
    private int defaultTimeout;
    private String databaseUrl;
    private String databaseSchema;

    private static class Holder {
        static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    private AppConfig() {
        propertyLoader = Config.getInstance().getPropertyLoader();
        loadProperties();
    }

    public void logSettings() {
        LOGGER.info("Environment:        " + Config.getInstance().getEnvironment());
        LOGGER.info("url:                " + baseUrl);

        WebDriverConfig webDriverConfig = WebDriverConfig.getInstance();
        LOGGER.info("Browser:            " + webDriverConfig.getBrowserProvider());

        if (!webDriverConfig.getBrowserDimension().isEmpty()) {
            LOGGER.info("browserSize:        " + webDriverConfig.getBrowserDimension());
        }

        LOGGER.info("Default Timeout:        " + getDefaultTimeout());
    }

    private void loadProperties() {
        baseUrl = propertyLoader.getProperty("baseUrl");
        searchUrl = propertyLoader.getProperty("searchUrl");
        defaultTimeout = propertyLoader.getPropertyAsInteger("webdriver.defaultTimeout", "10");
        databaseUrl = propertyLoader.getProperty("databaseUrl");
        databaseSchema = propertyLoader.getProperty("databaseSchema");
    }

    // Application properties
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public int getDefaultTimeout() {
        return defaultTimeout;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseSchema() {
        return databaseSchema;
    }
}
