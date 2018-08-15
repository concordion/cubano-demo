package org.concordion.cubano.template.driver.services;

import java.io.IOException;

import org.concordion.cubano.driver.http.HttpEasy;

public class ExampleRestApi extends ServiceBase {

    public String getIPAddress() throws IOException {
        // NOTE: HttpEasy config has already been set in HttpEasyConfigurator

        String response = HttpEasy.request()
                .baseUrl("http://httpbin.org")
                .path("get")
                .queryParam("name", "fred")
                .get()
                .getJsonReader()
                .asPrettyString();

        // Log response details in both log file and storyboard
        captureAction(Action.JSON_RESPONSE, "Get IP Address", "", response);

        return response;
    }
}
