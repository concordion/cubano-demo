package example.service;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.services.ExampleRestApi;

import java.io.IOException;

public class RestRequestFixture extends CubanoDemoBrowserFixture {

    public boolean callService() throws IOException {
        return new ExampleRestApi()
                .getIPAddress() != null;
    }
}
