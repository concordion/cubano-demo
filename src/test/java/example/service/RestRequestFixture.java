package example.service;

import example.CubanoDemoFixture;
import org.concordion.cubano.template.driver.services.ExampleRestApi;

import java.io.IOException;

public class RestRequestFixture extends CubanoDemoFixture {

    public boolean callService() throws IOException {
        return new ExampleRestApi()
                .getIPAddress() != null;
    }
}
