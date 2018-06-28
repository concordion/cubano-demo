package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionIsATestRunnerPage extends PageObject<ConcordionIsATestRunnerPage> {

    public ConcordionIsATestRunnerPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h3[text()='Concordion is a test runner']")
    WebElement isATestRunner;

    @FindBy(xpath = "//a[text() = '@nigel_charman']")
    WebElement projectLeadConcordion;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {
        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(isATestRunner))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(projectLeadConcordion));
    }

    public ConcordionForExecutableSpecsPage navigateToExecutableSpecifications() {

        return capturePageAndClick(navigateRight, ConcordionForExecutableSpecsPage.class);
    }
}
