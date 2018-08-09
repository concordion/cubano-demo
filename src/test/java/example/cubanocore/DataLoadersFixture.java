package example.cubanocore;

import java.io.IOException;

import org.concordion.cubano.data.JsonLoader;
import org.concordion.cubano.driver.http.JsonReader;
import org.concordion.cubano.template.driver.domain.User;
import org.concordion.ext.StoryboardMarkerFactory;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;
import org.concordion.slf4j.ext.MediaType;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import example.CubanoDemoFixture;

public class DataLoadersFixture extends CubanoDemoFixture {

    private final ReportLogger log = ReportLoggerFactory.getReportLogger(DataLoadersFixture.class);

    public boolean loadJsonFile() throws IOException {

        getStoryboard().addSectionContainer("Loading user.json");

        User user = JsonLoader.loadFile("example/cubanocore/user.json", User.class);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        addNotification("User", gson.toJson(user));

        return true;
    }

    public boolean loadJsonString() throws IOException {

        getStoryboard().addSectionContainer("Loading User from String");
        
        String userString = "{ \"username\": \"bob007\", \"fullName\": \"Bob Secret Agent\", \"password\": \"shhhh\"}";
        
        JsonReader jsonReader = new JsonReader(userString);

        addNotification("User", jsonReader.asPrettyString());

        return true;
    }

    protected void addNotification(String name, String data) {
        log
                .with()
                .message(name)
                .attachment(data, name + ".json", MediaType.PLAIN_TEXT)
                .marker(StoryboardMarkerFactory.addCard(
                        name,
                        StockCardImage.JSON, CardResult.SUCCESS))
                .debug();
    }

}
