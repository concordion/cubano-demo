package example;

import org.concordion.api.ConcordionResources;
import org.concordion.api.FailFast;
import org.concordion.cubano.framework.ConcordionBase;

/**
 * Customise index specification.
 */
@ConcordionResources("/customConcordion.css")
@FailFast
public abstract class CubanoDemoIndex extends ConcordionBase {
}
