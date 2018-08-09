package example.google;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.workflow.Workflow;

public class SearchForConcordionFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public String google(String term, String site) {
        return workflow
                .openSearch()
                .searchFor(term)
                .getSearchResult(site);
    }
}
