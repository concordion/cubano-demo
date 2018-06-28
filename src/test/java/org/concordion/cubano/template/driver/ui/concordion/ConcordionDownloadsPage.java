package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionDownloadsPage extends PageObject<ConcordionDownloadsPage> {

    public ConcordionDownloadsPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h3[text()='Downloads']")
    WebElement downloads;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(downloads))
                .and(ExpectedConditions.elementToBeClickable(navigateRight));
    }

    public ConcordionHowItWorksPage navigateToHowItWorks() {

        return capturePageAndClick(navigateRight, ConcordionHowItWorksPage.class);
    }
}
