package example.cubanoconcordion;

import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;
import org.concordion.cubano.template.framework.CubanoDemoBrowserFixture;

public class ExceptionHtmlCaptureExtensionFixture extends CubanoDemoBrowserFixture {

    public boolean noSuchElementExtension() {

        GoogleSearchPage.open(this)
                .searchForElementNotFound();

        return true;
    }
}
