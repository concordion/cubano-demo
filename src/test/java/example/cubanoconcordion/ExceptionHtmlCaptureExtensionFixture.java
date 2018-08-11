package example.cubanoconcordion;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;

public class ExceptionHtmlCaptureExtensionFixture extends CubanoDemoBrowserFixture {

    public boolean noSuchElementExtension() {

        GoogleSearchPage.open(this)
                .searchForElementNotFound();

        return true;
    }
}
