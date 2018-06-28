package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionSpecificationPage extends PageObject<ConcordionSpecificationPage> {

    public ConcordionSpecificationPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h1[text()='Specification']")
    WebElement specification;

    @FindBy(xpath = "//h2[text()='How it works']")
    WebElement howItWorks;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(specification))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(howItWorks));
    }

    public ConcordionInstrumentationPage navigateToInstrumentation() {

        return capturePageAndClick(navigateRight, ConcordionInstrumentationPage.class);
    }
}
