package example.cubanowebdriver;

import org.concordion.cubano.driver.web.Browser;
import org.concordion.cubano.driver.web.provider.ChromeBrowserProvider;
import org.concordion.cubano.template.driver.ui.google.GoogleSearchPage;
import org.concordion.cubano.template.fixture.CubanoDemoBrowserFixture;

import java.io.IOException;

public class SwitchBrowser extends CubanoDemoBrowserFixture {
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
        GoogleSearchPage.open(this)
                .searchFor("Concordion")
                .getSearchResult("https://concordion.org");
    }
}
