package example.cubanoconcordion;

import example.ConcordionFixture;

public class ExceptionHtmlCaptureExtensionFixture extends ConcordionFixture {

    public boolean noSuchElementExtension() {

        workflow()
                .openSearch()
                .searchForElementNotFound();

        return true;
    }
}
