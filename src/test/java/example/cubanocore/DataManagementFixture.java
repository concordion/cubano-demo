package example.cubanocore;

import org.concordion.cubano.template.driver.domain.AbcDomainData;
import org.concordion.cubano.template.driver.services.AbcService;

import example.ConcordionFixture;

public class DataManagementFixture extends ConcordionFixture {

    public boolean manageDataSetUpAndTearDown() {

        // Simplistic. Implement a strategy to load some test data.
        // Load from File/Database/Builder Pattern
        // Add to Virtual/Mock/Stubbed data repository
        AbcDomainData abcDomainData = new AbcDomainData();
        abcDomainData.setDomainDataId(001);
        abcDomainData.setNameOfTheDomain("AbcDomain");

        // Initiate your service
        AbcService abcService = workflowUsingDataCleanupHelper().businessDomainScenario(abcDomainData);

        // Use the data set up on the Service
        int domainDataId = abcService.getAbcDomainData().getDomainDataId();

        return true;
    }
}
