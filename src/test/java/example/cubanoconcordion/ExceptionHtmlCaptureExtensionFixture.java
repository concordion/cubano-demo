package example.cubanoconcordion;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.workflow.Workflow;

public class ExceptionHtmlCaptureExtensionFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public boolean noSuchElementExtension() {

        workflow
                .openSearch()
                .searchForElementNotFound();

        return true;
    }
}
