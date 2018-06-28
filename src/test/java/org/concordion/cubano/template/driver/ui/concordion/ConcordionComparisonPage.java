package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionComparisonPage extends PageObject<ConcordionComparisonPage> {

    public ConcordionComparisonPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h2[text()='Comparison']")
    WebElement comparison;

    @FindBy(xpath = "//h2[text()='System under Test']")
    WebElement sut;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(comparison))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(sut));
    }

    public ConcordionExtensionsPage navigateToExtensions() {

        // Loads the 8 comparison elements
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();
        navigateRight.click();

        return capturePageAndClick(navigateRight, ConcordionExtensionsPage.class);
    }
}
