package example.commands;

import org.concordion.cubano.template.fixture.CubanoDemoFixture;

public class SetCommandFixture extends CubanoDemoFixture {

    public String hello(String name) throws Exception {

        return "Hello " + name;
    }

}
