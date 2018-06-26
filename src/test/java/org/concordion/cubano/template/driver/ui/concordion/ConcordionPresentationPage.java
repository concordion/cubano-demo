package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionPresentationPage extends PageObject<ConcordionPresentationPage> {

    public ConcordionPresentationPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//a[text() = '@nigel_charman']")
    WebElement projectLeadConcordion;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {
        return ExpectedConditions.visibilityOf(projectLeadConcordion);
    }

    public static ConcordionPresentationPage open(BrowserBasedTest test) {
        test.getBrowser().getDriver().navigate().to(AppConfig.getInstance().getConcordionPresentationUrl());

        return new ConcordionPresentationPage(test);
    }

    public ConcordionIsATestRunnerPage navigateToConcordionIsATestRunner() {
        
        capturePage(navigateRight);

        getTest().getBrowser().getDriver().navigate().to(AppConfig.getInstance().getConcordionPresentationUrl() + "2");

        return new ConcordionIsATestRunnerPage(getTest());
    }

}
