package example.cubanohttpeasy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import java.io.IOException;

import example.CubanoDemoFixture;
import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.driver.http.JsonReader;
import org.concordion.cubano.template.driver.logger.TestLoggerLogWriter;
import org.concordion.ext.StoryboardMarkerFactory;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.StockCardImage;
import org.concordion.slf4j.ext.ReportLogger;
import org.concordion.slf4j.ext.ReportLoggerFactory;
import org.junit.Rule;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.net.MediaType;

public class HttpEasyPostFixture extends CubanoDemoFixture {

    private static final String HTTP_EASY_POST = "/HttpEasyPost";

    private final ReportLogger log = ReportLoggerFactory.getReportLogger(HttpEasyPostFixture.class);

    private static final String DEFAULT_WIREMOCK_HOST = "http://localhost:";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9898);

    public boolean httpEasyPostResponse() throws IOException {
        
        HttpEasy easy = HttpEasy.request().baseURI(DEFAULT_WIREMOCK_HOST + wireMockRule.port());

        setupStub();

        TestLoggerLogWriter testLoggerLogWriter = new TestLoggerLogWriter();

        // TODO - update Cubano > HttpEasy > Log > to log data
        JsonReader jsonReader = easy
                .path(HTTP_EASY_POST)
                .data("{\"Hello\": \"Here is my POST in the request\"}", MediaType.JSON_UTF_8)
                .withLogWriter(testLoggerLogWriter)
                .logRequestDetails()
                .post()
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
                .attachment(data, name + ".json", org.concordion.slf4j.ext.MediaType.PLAIN_TEXT)
                .marker(StoryboardMarkerFactory.addCard(
                        name,
                        StockCardImage.JSON, CardResult.SUCCESS))
                .debug();
    }

    private void setupStub() {
        stubFor(post(urlPathMatching(HTTP_EASY_POST))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"Hello\": \"Here is my POST\"}")));
    }
}