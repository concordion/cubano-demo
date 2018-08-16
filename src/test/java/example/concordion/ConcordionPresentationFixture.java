package example.concordion;

import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.concordion.ConcordionPresentationPage;
import org.concordion.cubano.template.fixture.CubanoDemoBrowserFixture;

public class ConcordionPresentationFixture extends CubanoDemoBrowserFixture {

    public String getConcordionPressoURL() {

        return AppConfig.getInstance().getConcordionPresentationUrl();
    }

    public void loadDoco() throws Exception {

        ConcordionPresentationPage.open(this)
                .navigateToConcordionIsATestRunner()
                .navigateToExecutableSpecifications()
                .navigateToBeautifulLivingDocumentation()
                .navigateToHowItWorks()
                .navigateToSpecification()
                .navigateToInstrumentation()
                .navigateToFixture()
                .navigateToSystemUnderTest()
                .navigateToComparison()
                .navigateToExtensions()
                .navigateToLearnMore();
    }

}
