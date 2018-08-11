package example.cubanocore;

import example.CubanoDemoFixture;
import org.codejargon.fluentjdbc.api.FluentJdbcException;
import org.concordion.cubano.framework.resource.ResourceScope;
import org.concordion.cubano.template.driver.database.DataManagementDatabase;
import org.concordion.cubano.template.driver.domain.AbcDomainData;
import org.concordion.cubano.template.driver.services.AbcService;

import java.sql.SQLException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataManagementFixture extends CubanoDemoFixture {

    private DataManagementDatabase database = new DataManagementDatabase();
    private AbcService abcService = new AbcService();
    private static AbcDomainData domainData;

    private void initialiseDB() throws SQLException {
        database.initialiseDatabase();
    }

    public void insertData() throws SQLException {
        // Only here for the test setup. Would normally exist as part of the SUT
        initialiseDB();

        domainData = new AbcDomainData();
        domainData.setDomainDataId(UUID.randomUUID().toString());
        domainData.setNameOfTheDomain("AbcDomain");

        // Initiate your service
        abcService.setAbcDomainData(domainData);
        abcService.submitDomainScenario();
    }

    public boolean existsInDatabase() throws SQLException {
        try {
            // Check is in the DB
            AbcDomainData actualDomainData = database.findAbcDomainUsing(domainData);
            assertThat(actualDomainData.getDomainDataId(), is(domainData.getDomainDataId()));

            return true;
        } catch(FluentJdbcException e) {
            return false;
        }
    }

    public void registerCloseableResource() {
        registerCloseableResource(abcService, ResourceScope.EXAMPLE);
    }
}
