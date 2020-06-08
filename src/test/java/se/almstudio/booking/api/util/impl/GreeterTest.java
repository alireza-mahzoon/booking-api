package se.almstudio.booking.api.util.impl;

import org.junit.Assert;
import org.junit.Test;

public class GreeterTest {

    @Test
    public void testUseNoneNullNameExpectNameInGreeting() {
        String name = "Erfan";
        Greeter greeter = new Greeter();
        String result = greeter.greet(name);
        Assert.assertEquals("Hello Erfan", result);
    }

    @Test
    public void testUseNullNameExpectStrangerInGreeting() {
        Greeter greeter = new Greeter();
        String result = greeter.greet(null);
        Assert.assertEquals("Hello stranger", result);
    }
}
