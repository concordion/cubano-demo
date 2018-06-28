package example.service;

import java.io.IOException;

import example.ConcordionFixture;

public class RestRequestFixture extends ConcordionFixture {

    public boolean callService() throws IOException {
        return workflow()
                .restExample()
                .getIPAddress() != null;
    }
}
