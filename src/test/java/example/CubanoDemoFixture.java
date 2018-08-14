package example;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.cubano.framework.ConcordionFixture;
import org.concordion.cubano.template.driver.httpeasy.HttpEasyConfigurator;

/**
 * A base class for extension by fixtures that contain assertions.
 *
 * @see CubanoDemoIndex for fixtures that don't contain assertions
 * @see CubanoDemoBrowserFixture for fixtures that invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoFixture extends ConcordionFixture {
    static {
        HttpEasyConfigurator.applyTrustingConfig();
    }

    /** Override the default logger. **/
    public CubanoDemoFixture() {
        super.withFixtureListener(new CubanoDemoFixtureLogger());
    }
}
