package example.cubanocore;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.UUID;

import org.concordion.cubano.template.driver.database.DataManagementDatabase;
import org.concordion.cubano.template.driver.domain.AbcDomainData;

import example.ConcordionFixture;

public class DataManagementFixture extends ConcordionFixture {

    private DataManagementDatabase database = new DataManagementDatabase();

    private void initiliaseDB() throws SQLException {
        database.initialiseDatabase();
    }

    public boolean manageDataSetUpAndTearDown() throws SQLException {

        // Only here for the test setup. Would normally exist as part of the SUT
        initiliaseDB();

        AbcDomainData expectedDomainData = new AbcDomainData();
        expectedDomainData.setDomainDataId(UUID.randomUUID().toString());
        expectedDomainData.setNameOfTheDomain("AbcDomain");

        // Initiate your service
        workflowUsingDataCleanupHelper().addDataToServiceAndRegisterServiceOnCleanUp(expectedDomainData).submitDomainScenario();

        // Check is in the DB
        AbcDomainData actualDomainData = database.findAbcDomainUsing(expectedDomainData);
        assertThat(actualDomainData.getDomainDataId(), is(expectedDomainData.getDomainDataId()));

        // Do what you need to do
        // Execute test scenarios

        // Clean up happens in ConcordionFixture @ afterExample


        return true;
    }
}
