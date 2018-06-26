package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionHowItWorksPage extends PageObject<ConcordionHowItWorksPage> {

    public ConcordionHowItWorksPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h2[text()='How it works']")
    WebElement howItWorks;

    @FindBy(xpath = "//h3[text()='Downloads']")
    WebElement downloads;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(howItWorks))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(downloads));
    }

    public ConcordionSpecificationPage navigateToSpecification() {

        return capturePageAndClick(navigateRight, ConcordionSpecificationPage.class);
    }
}
