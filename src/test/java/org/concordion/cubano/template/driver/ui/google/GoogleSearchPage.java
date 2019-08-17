package org.concordion.cubano.template.driver.ui.google;

import java.util.List;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.template.AppConfig;
import org.concordion.cubano.template.driver.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleSearchPage extends PageObject<GoogleSearchPage> {
    public GoogleSearchPage(BrowserBasedTest test) {
        super(test);
    }

    @FindBy(name = "q")
    WebElement query;

    @FindBy(name = "elementNotFound")
    WebElement elementNotFound;

    @FindBy(css = "div.rc")
    List<SearchResult> searchResults;

    @Override
    public ExpectedCondition<?> pageIsLoaded(Object... params) {
        return ExpectedConditions.visibilityOf(query);
    }

    public static GoogleSearchPage open(BrowserBasedTest test) {
        test.getBrowser().getDriver().navigate().to(AppConfig.getInstance().getSearchUrl());

        return new GoogleSearchPage(test);
    }

    public GoogleSearchPage searchForElementNotFound() {

        elementNotFound.sendKeys("you won't find me");
        return this;
    }

    public GoogleSearchPage searchFor(String term) {

        query.sendKeys(term);
        capturePage(query);

        query.submit();

        waitUntil(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")), 3);

        return this;
    }

    public String getSearchResult(String link) {
        for (SearchResult searchResult : searchResults) {
            String url = searchResult.url.getText();

            if (url.contains(link)) {
                return url;
            }
        }

        return null;
    }
}
