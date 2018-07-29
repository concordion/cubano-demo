package example.service;

import example.CubanoDemoBrowserFixture;
import org.concordion.cubano.template.driver.workflow.Workflow;

import java.io.IOException;

public class RestRequestFixture extends CubanoDemoBrowserFixture {
    private Workflow workflow = new Workflow(this);

    public boolean callService() throws IOException {
        return workflow
                .restExample()
                .getIPAddress() != null;
    }
}
