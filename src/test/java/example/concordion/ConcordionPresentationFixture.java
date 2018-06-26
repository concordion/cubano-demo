package example.concordion;

import org.concordion.cubano.template.AppConfig;

import example.ConcordionFixture;

public class ConcordionPresentationFixture extends ConcordionFixture {

    public String getConcordionPressoURL() {

        return AppConfig.getInstance().getConcordionPresentationUrl();
    }

    public void loadDoco() throws Exception {

        workflow().openConcordionPresentation()
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
