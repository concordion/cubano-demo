package example;

import org.concordion.api.extension.Extension;
import org.concordion.cubano.framework.ConcordionFixture;
import org.concordion.ext.LogbackLogMessenger;
import org.concordion.ext.LoggingTooltipExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

/**
 */
public abstract class CubanoDemoFixture extends ConcordionFixture {
    private final Logger tooltipLogger = LoggerFactory.getLogger("TOOLTIP_" + this.getClass().getName());

    @Extension
    private final LoggingTooltipExtension tooltipExtension = new LoggingTooltipExtension(new LogbackLogMessenger(tooltipLogger.getName(), Level.ALL, true, "%msg%n"));

    /** Override the default logger. **/
    public CubanoDemoFixture() {
        super.withFixtureListener(new CubanoDemoFixtureLogger());
    }

    public void addConcordionTooltip(final String message) {
        // Logging at debug level means the message won't make it to the console, but will make
        // it to the logs (based on included logback configuration files)
        tooltipLogger.debug(message);
    }
}
