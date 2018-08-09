package example.google;

import org.concordion.cubano.template.driver.workflow.Workflow;

import example.CubanoDemoBrowserFixture;

public class SearchForConcordionFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public String google(String term, String site) {
        return workflow
                .openSearch()
                .searchFor(term)
                .getSearchResult(site);
    }
}
