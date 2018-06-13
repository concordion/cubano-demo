package example.google;

import example.ConcordionFixture;

public class SearchForConcordionFixture extends ConcordionFixture {

    public String google(String term, String site) {
        return workflow()
                .openSearch()
                .searchFor(term)
                .getSearchResult(site);
    }
}
