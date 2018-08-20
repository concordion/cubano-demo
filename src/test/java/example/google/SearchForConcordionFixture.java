package example.google;

import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;
import org.concordion.cubano.template.framework.CubanoDemoBrowserFixture;

public class SearchForConcordionFixture extends CubanoDemoBrowserFixture {
    public String google(String term, String site) {
        return GoogleSearchPage.open(this)
                .searchFor(term)
                .getSearchResult(site);
    }
}
