package org.concordion.cubano.template.driver.ui.google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class SearchResult extends HtmlElement {
    @FindBy(className = "r")
    WebElement heading;

    @FindBy(tagName = "cite")
    WebElement url;

    @FindBy(css = ".s .st")
    WebElement description;
}
