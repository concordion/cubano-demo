package org.concordion.cubano.template.driver.workflow;

import org.concordion.cubano.data.DataCleanupHelper;
import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.template.driver.domain.AbcDomainData;
import org.concordion.cubano.template.driver.services.AbcService;
import org.concordion.cubano.template.driver.services.ExampleRestApi;
import org.concordion.cubano.template.driver.ui.concordion.ConcordionPresentationPage;
import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;

public class Workflow {
    private final BrowserBasedTest test;
    private DataCleanupHelper dataCleanup = null;


    public Workflow(BrowserBasedTest test) {
        this.test = test;
    }

    public Workflow(BrowserBasedTest test, DataCleanupHelper dataHelper) {
        this.test = test;
        this.dataCleanup = dataHelper;
    }

    public GoogleSearchPage openSearch() {
        return GoogleSearchPage.open(test);
    }

    public ConcordionPresentationPage openConcordionPresentation() {
        return ConcordionPresentationPage.open(test);
    }

    public ExampleRestApi restExample() {
        return new ExampleRestApi();
    }

    public AbcService addDataToServiceAndRegisterServiceOnCleanUp(AbcDomainData abcDomainData) {
        AbcService abcService = new AbcService(abcDomainData);
//        dataCleanup.register(abcService);

        return abcService;
    }

}
