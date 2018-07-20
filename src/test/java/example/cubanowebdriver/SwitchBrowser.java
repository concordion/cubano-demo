package example.cubanowebdriver;

import java.io.IOException;

import org.concordion.cubano.driver.web.Browser;
import org.concordion.cubano.driver.web.provider.ChromeBrowserProvider;

import example.ConcordionFixture;

public class SwitchBrowser extends ConcordionFixture {

    public boolean switchBrowser() throws IOException {

        getStoryboard().addSectionContainer("Seach Google using Browser.DEFAULT");
        // Browser Resolves to setting for 'webdriver.browserprovider' in config.properties
        searchGoogle();

        // Could extract "FF-SECOND-BROWSER" to a key to be used later
        // switchBrowser("FF-SECOND-BROWSER", new FirefoxBrowserProvider());

        getStoryboard().addSectionContainer("Switch to Chrome");
        switchBrowser("CHROME", new ChromeBrowserProvider());

        // Now we are in IE
        searchGoogle();

        getStoryboard().addSectionContainer("Switch back to Browser.DEFAULT ");

        // 'Browser.DEFAULT' > Resolves to setting for 'webdriver.browserprovider' in config.properties
        switchBrowser(Browser.DEFAULT);

        // Now we are back in the default
        searchGoogle();

        return true;
    }

    private void searchGoogle() {
        workflow()
                .openSearch()
                .searchFor("Concordion")
                .getSearchResult("https://concordion.org");
    }
}
