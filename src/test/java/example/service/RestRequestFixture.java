package example.service;

import java.io.IOException;

import org.concordion.cubano.template.driver.workflow.Workflow;

import example.CubanoDemoBrowserFixture;

public class RestRequestFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public boolean callService() throws IOException {
        return workflow
                .restExample()
                .getIPAddress() != null;
    }
}
