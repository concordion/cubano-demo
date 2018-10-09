package org.concordion.cubano.template.driver.ui.google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class SearchResult extends HtmlElement {

    @FindBy(css = ".TbwUpd .iUh30")
    WebElement url;
}
