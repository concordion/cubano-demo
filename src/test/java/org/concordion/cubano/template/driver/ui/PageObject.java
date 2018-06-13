package org.concordion.cubano.template.driver.ui;

import org.concordion.cubano.driver.BrowserBasedTest;
import org.concordion.cubano.driver.action.ActionTimer;
import org.concordion.cubano.driver.web.BasePageObject;
import org.concordion.cubano.driver.web.PageReadyConditions;
import org.concordion.cubano.template.AppConfig;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Object that all pages inherit from, this is customised to each application.
 * 
 * @param <T> The class of the PageObject that is extending this class.
 * 
 * @author sumnera
 */
public abstract class PageObject<T extends PageObject<T>> extends BasePageObject<T> {
    public PageObject(BrowserBasedTest test) {
        this(test, AppConfig.getInstance().getDefaultTimeout());
    }

    public PageObject(BrowserBasedTest test, int timeoutWaitInSeconds, Object... params) {
        super(test, timeoutWaitInSeconds, params);
    }

    /**
     * Returns a condition that checks whether the page is loaded. This is called when constructing a new PageObject to check that it is in sync with
     * the browser.
     * <p/>
     * Since WebDriver can return prior to a page being fully loaded, this condition must be unique to the page being loaded and must not be met by
     * the previous page that we are transferring from.
     * 
     * @param params List of parameters that where passed in to the constructor
     * @return An ExpectedCondition
     */
    public abstract ExpectedCondition<?> pageIsLoaded(Object... params);

    @Override
    protected void waitUntilPageIsLoaded(int timeoutWaitInSeconds, Object... params) {
        getLogger().debug("Wait for any ajax requests to complete");

        switchToMainDocument();
        waitUntilAjaxRequestsCompleted();

        ActionTimer timer = ActionTimer.start(getLogger(), "Checking {} loads within {} seconds", getSimpleName(), timeoutWaitInSeconds);

        // Wait for the page to complete loading
        if (pageIsLoaded(params) != null) {
            waitUntil(pageIsLoaded(params), timeoutWaitInSeconds);
        }

        getLogger().with()
                .htmlMessage("{} loaded in {} seconds <br /><span class=\"greyed\">Current url: {}<span>",
                        getSimpleName(), timer.duration().getSeconds(), getBrowser().getDriver().getCurrentUrl())
                .trace();
    }

    /**
     * As a single page application WebDriver does not wait for a "new page" to load
     * when click on button/link, instead the Ajax request is fired off and WebDriver passes control
     * back to the test. The will ensure that any Ajax requests have completed before moving on.
     * 
     * NOTE: this assumes that we are using jQuery.
     */
    protected void waitUntilAjaxRequestsCompleted() {
        waitUntil(PageReadyConditions.noActiveAjaxRequest(), 90);
        waitUntil(PageReadyConditions.noVisibleSpinners(), 30);
    }

}
