package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionBeautifulLivingDocsPage extends PageObject<ConcordionBeautifulLivingDocsPage> {

    public ConcordionBeautifulLivingDocsPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h3[text()='Concordion is a test runner']")
    WebElement isATestRunner;

    @FindBy(xpath = "//span[text()='for executable specifications']")
    WebElement forExecutableSpecifications;

    @FindBy(xpath = "//span[text()='that creates beautiful living documentation']")
    WebElement createsBeautifulLivingDocumentation;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(isATestRunner))
                .and(ExpectedConditions.visibilityOf(forExecutableSpecifications))
                .and(ExpectedConditions.visibilityOf(createsBeautifulLivingDocumentation));
    }

    public ConcordionHowItWorksPage navigateToHowItWorks() {

        capturePage(navigateRight);

        getTest().getBrowser().getDriver().navigate().to(AppConfig.getInstance().getConcordionPresentationUrl() + "5");

        return new ConcordionHowItWorksPage(getTest());
    }
}
