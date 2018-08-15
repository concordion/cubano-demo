package example;

import java.io.Closeable;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.api.extension.Extension;
import org.concordion.cubano.driver.concordion.ExceptionHtmlCaptureExtension;
import org.concordion.cubano.framework.ConcordionBrowserFixture;
import org.concordion.cubano.framework.resource.CloseListener;
import org.concordion.cubano.framework.resource.ResourceScope;
import org.concordion.cubano.template.driver.httpeasy.HttpEasyConfigurator;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

/**
 * A base class for extension by fixtures that invoke a browser, and may also use HttpEasy.
 *
 * Customises the test specification and provides some helper methods so the tests can access the storyboard, browser, etc.
 *
 * @see CubanoDemoIndex for fixtures that don't contain assertions
 * @see CubanoDemoFixture for fixtures that don't invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoBrowserFixture extends ConcordionBrowserFixture {
    protected final ReportLogger reportLogger = ReportLoggerFactory.getReportLogger(this.getClass().getName());

    @Extension
    private final ExceptionHtmlCaptureExtension htmlCapture = new ExceptionHtmlCaptureExtension(getStoryboard(), getBrowser());

    static {
        HttpEasyConfigurator.applyTrustingConfig();
    }

    /** Override the default fixture logger. **/
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
                getStoryboard().setAcceptCards(true);
            }
        };
        super.registerCloseableResource(resource, scope, listener);
    }
}
