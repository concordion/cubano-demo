package example.cubanoconcordion;

import org.concordion.cubano.template.driver.workflow.Workflow;

import example.CubanoDemoBrowserFixture;

public class ExceptionHtmlCaptureExtensionFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public boolean noSuchElementExtension() {

        workflow
                .openSearch()
                .searchForElementNotFound();

        return true;
    }
}
