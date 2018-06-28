package org.concordion.cubano.template.driver.ui.concordion;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.web.ChainExpectedConditions;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConcordionSUTPage extends PageObject<ConcordionSUTPage> {

    public ConcordionSUTPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(className = "navigate-right")
    WebElement navigateRight;

    @FindBy(xpath = "//h2[text()='System under Test']")
    WebElement sut;

    @FindBy(xpath = "//h1[text()='Fixture']")
    WebElement fixture;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {

        return ChainExpectedConditions
                .with(ExpectedConditions.visibilityOf(sut))
                .and(ExpectedConditions.elementToBeClickable(navigateRight))
                .and(ExpectedConditions.invisibilityOf(fixture));
    }

    public ConcordionComparisonPage navigateToComparison() {

        capturePage(navigateRight);

        getTest().getBrowser().getDriver().navigate().to(AppConfig.getInstance().getConcordionPresentationUrl() + "12");

        return new ConcordionComparisonPage(getTest());
    }
}
