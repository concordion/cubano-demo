package example.commands;

import example.CubanoDemoFixture;

public class SetCommandFixture extends CubanoDemoFixture {

    public String hello(String name) throws Exception {

        return "Hello " + name;
    }

}
