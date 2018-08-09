package example.concordion;

import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.workflow.Workflow;

import example.CubanoDemoBrowserFixture;

public class ConcordionPresentationFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public String getConcordionPressoURL() {

        return AppConfig.getInstance().getConcordionPresentationUrl();
    }

    public void loadDoco() throws Exception {

        workflow.openConcordionPresentation()
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
