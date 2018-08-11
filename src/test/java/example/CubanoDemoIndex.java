package example;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.cubano.framework.ConcordionBase;

/**
 * A base class for extension by fixtures which relate to "index" specifications containing no assertions.
 *
 * @see CubanoDemoFixture for fixtures that don't invoke a browser
 * @see CubanoDemoBrowserFixture for fixtures that invoke a browser
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoIndex extends ConcordionBase {
}
