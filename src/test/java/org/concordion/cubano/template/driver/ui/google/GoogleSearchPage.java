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
        List<SearchResult> searchResults = getBrowser().getHtmlElementsLoader(this).findElements(SearchResult.class, By.cssSelector("div.rc .r"));

        for (SearchResult searchResult : searchResults) {
            String url = searchResult.url.getText();

            if (url.startsWith(link)) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }

                return url;
            }
        }

        return null;
    }
}
