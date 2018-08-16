package org.concordion.cubano.template.fixture;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.cubano.framework.ConcordionFixture;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.httpeasy.HttpEasyConfigurator;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

/**
 * A base class for extension by fixtures that contain assertions.
 *
 * @see CubanoDemoIndex for fixtures that don't contain assertions
 * @see CubanoDemoBrowserFixture for fixtures that invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoFixture extends ConcordionFixture {
    protected final ReportLogger reportLogger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    static {
        AppConfig.getInstance().logSettings();

        HttpEasyConfigurator.applyDefaultSettings();
    }

    /** Override the default logger. **/
    public CubanoDemoFixture() {
        super.withFixtureListener(new CubanoDemoFixtureLogger());
    }
}
