package example.service;

import org.concordion.cubano.template.driver.services.ExampleRestApi;
import org.concordion.cubano.template.framework.CubanoDemoFixture;

import java.io.IOException;

public class RestRequestFixture extends CubanoDemoFixture {

    public boolean callService() throws IOException {
        return new ExampleRestApi()
                .getIPAddress() != null;
    }
}
