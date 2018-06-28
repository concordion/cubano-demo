package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionExtensionsPage extends PageObject<ConcordionExtensionsPage> {

    public ConcordionExtensionsPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h2[text()='Extensions']")
    WebElement extensions;

    @FindBy(xpath = "//h2[text()='Comparison']")
    WebElement comparison;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(extensions))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(comparison));
    }

    public ConcordionLearnMorePage navigateToLearnMore() {
        return capturePageAndClick(navigateRight, ConcordionLearnMorePage.class);
    }
}
