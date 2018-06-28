package example.commands;

import example.ConcordionFixture;

public class SetCommandFixture extends ConcordionFixture {

    public String hello(String name) throws Exception {

        return "Hello " + name;
    }

}
