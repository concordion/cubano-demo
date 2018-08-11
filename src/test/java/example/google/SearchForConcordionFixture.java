package example.google;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;

public class SearchForConcordionFixture extends CubanoDemoBrowserFixture {
    public String google(String term, String site) {
        return GoogleSearchPage.open(this)
                .searchFor(term)
                .getSearchResult(site);
    }
}
