package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionInstrumentationPage extends PageObject<ConcordionInstrumentationPage> {

    public ConcordionInstrumentationPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h1[text()='Instrumentation']")
    WebElement instrumentation;

    @FindBy(xpath = "//h1[text()='Specification']")
    WebElement specification;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(instrumentation))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(specification));
    }

    public ConcordionFixturePage navigateToFixture() {

        return capturePageAndClick(navigateRight, ConcordionFixturePage.class);
    }
}
