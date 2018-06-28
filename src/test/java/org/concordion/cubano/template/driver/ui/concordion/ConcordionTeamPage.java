package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionTeamPage extends PageObject<ConcordionTeamPage> {

    public ConcordionTeamPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(className = "present")
    WebElement concordionTeam;

    @FindBy(xpath = "//h3[text()='Concordion is a test runner']")
    WebElement isATestRunner;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(concordionTeam))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(isATestRunner));
    }

    public ConcordionDownloadsPage navigateToDownloads() {

        return capturePageAndClick(navigateRight, ConcordionDownloadsPage.class);
    }
}
