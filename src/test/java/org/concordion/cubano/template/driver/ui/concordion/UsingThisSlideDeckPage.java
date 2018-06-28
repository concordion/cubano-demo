package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsingThisSlideDeckPage extends PageObject<UsingThisSlideDeckPage> {

    public UsingThisSlideDeckPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h2[text()='Using this slide deck']")
    WebElement usingThisSlideDeck;

    @FindBy(xpath = "//a[text() = '@nigel_charman']")
    WebElement projectLeadConcordion;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {
        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(usingThisSlideDeck))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(projectLeadConcordion));
    }

    public ConcordionIsATestRunnerPage navigateToConcordionIsATestRunner() {

        return capturePageAndClick(navigateRight, ConcordionIsATestRunnerPage.class);
    }
}
