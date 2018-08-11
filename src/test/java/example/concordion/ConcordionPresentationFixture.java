package example.concordion;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.concordion.ConcordionPresentationPage;

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
