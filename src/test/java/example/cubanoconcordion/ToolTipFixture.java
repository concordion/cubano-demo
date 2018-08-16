package example.cubanoconcordion;

import ch.qos.logback.classic.Level;

import org.concordion.api.extension.Extension;
import org.concordion.cubano.template.framework.CubanoDemoFixture;
import org.concordion.ext.LogbackLogMessenger;
import org.concordion.ext.LoggingTooltipExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolTipFixture extends CubanoDemoFixture {

    private final Logger tooltipLogger = LoggerFactory.getLogger("TOOLTIP_" + this.getClass().getName());

    @Extension
    private final LoggingTooltipExtension tooltipExtension = new LoggingTooltipExtension(new LogbackLogMessenger(tooltipLogger.getName(), Level.ALL, true, "%msg%n"));

    public boolean addToolTipLogging() {

        addConcordionTooltip("Look at me, just hovering over here!");
        return true;
    }

    public void addConcordionTooltip(final String message) {
        // Logging at debug level means the message won't make it to the console, but will make
        // it to the logs (based on included logback configuration files)
        tooltipLogger.debug(message);
    }
}
