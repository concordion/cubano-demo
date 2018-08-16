package example.cubanohttpeasy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import java.io.IOException;

import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.driver.http.JsonReader;
import org.concordion.cubano.template.driver.logger.TestLoggerLogWriter;
import org.concordion.cubano.template.fixture.CubanoDemoFixture;
import org.concordion.ext.StoryboardMarkerFactory;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;
import org.concordion.slf4j.ext.MediaType;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;
import org.junit.Rule;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class HttpEasyGetFixture extends CubanoDemoFixture {

    private static final String HTTP_EASY_GET = "/HttpEasyGet";

    private final ReportLogger log = ReportLoggerFactory.getReportLogger(HttpEasyGetFixture.class);

    private static final String DEFAULT_WIREMOCK_HOST = "http://localhost:";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9898);

    public boolean httpEasyGetResponse() throws IOException {
        
        HttpEasy easy = HttpEasy.request().baseUrl(DEFAULT_WIREMOCK_HOST + wireMockRule.port());

        setupStub();

        TestLoggerLogWriter testLoggerLogWriter = new TestLoggerLogWriter();

        JsonReader jsonReader = easy
                .path(HTTP_EASY_GET)
                .withLogWriter(testLoggerLogWriter)
                .logRequestDetails()
                .get()
                .getJsonReader();

        addNotification("TestLoggerLogWriter Request", testLoggerLogWriter.getRequest());
        addNotification("Raw JSON Response", jsonReader.asPrettyString());
        addNotification("TestLoggerLogWriter Response", testLoggerLogWriter.getResponse());

        return true;
    }

    public boolean httpEasyGetResponseUsingParams() throws IOException {

        HttpEasy easy = HttpEasy.request().baseUrl(DEFAULT_WIREMOCK_HOST + wireMockRule.port());

        setupStub();

        TestLoggerLogWriter testLoggerLogWriter = new TestLoggerLogWriter();

        JsonReader jsonReader = easy
                .path(HTTP_EASY_GET)
                .queryParam("key1", "value1")
                .queryParam("key2", "value2")
                .withLogWriter(testLoggerLogWriter)
                .logRequestDetails()
                .get()
                .getJsonReader();

        addNotification("TestLoggerLogWriter Request", testLoggerLogWriter.getRequest());
        addNotification("Raw JSON Response", jsonReader.asPrettyString());
        addNotification("TestLoggerLogWriter Response", testLoggerLogWriter.getResponse());

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

    private void setupStub() {
        stubFor(get(urlPathMatching(HTTP_EASY_GET))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"Hello\": \"Here is my GET\"}")));
    }
}