package example;

import ch.qos.logback.classic.Level;
import org.concordion.api.*;
import org.concordion.api.extension.Extension;
import org.concordion.cubano.data.DataCleanupHelper;
import org.concordion.cubano.framework.ConcordionFixture;
import org.concordion.ext.LogbackLogMessenger;
import org.concordion.ext.LoggingTooltipExtension;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public abstract class CubanoDemoFixture extends ConcordionFixture {
    private final ReportLogger logger = ReportLoggerFactory.getReportLogger(this.getClass().getName());
    private final Logger tooltipLogger = LoggerFactory.getLogger("TOOLTIP_" + this.getClass().getName());

    @Extension
    private final LoggingTooltipExtension tooltipExtension = new LoggingTooltipExtension(new LogbackLogMessenger(tooltipLogger.getName(), Level.ALL, true, "%msg%n"));

    public void addConcordionTooltip(final String message) {
        // Logging at debug level means the message won't make it to the console, but will make
        // it to the logs (based on included logback configuration files)
        tooltipLogger.debug(message);
    }

    @BeforeExample
    private final void beforeExample(@ExampleName String exampleName) {
        logger.info("Starting example {} for test fixture {}", exampleName, getRelativeTestClassName());
    }

    private String getRelativeTestClassName() {
        // This is the name that can be given to the RunSingleTest job in Jenkins
        return this.getClass().getName().replace(CubanoDemoBrowserFixture.class.getPackage().getName() + ".", "");
    }
}
