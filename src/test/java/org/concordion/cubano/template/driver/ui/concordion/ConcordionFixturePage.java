package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionFixturePage extends PageObject<ConcordionFixturePage> {

    public ConcordionFixturePage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h1[text()='Fixture']")
    WebElement fixture;

    @FindBy(xpath = "//h1[text()='Instrumentation']")
    WebElement instrumentation;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(fixture))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(instrumentation));
    }

    public ConcordionSUTPage navigateToSystemUnderTest() {

        return capturePageAndClick(navigateRight, ConcordionSUTPage.class);
    }
}
