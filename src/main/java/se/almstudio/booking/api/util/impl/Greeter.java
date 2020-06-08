package se.almstudio.booking.api.util.impl;

public class Greeter {
    public String greet(String name) {
        if (name == null) {
            return "Hello stranger";
        }
        return "Hello " + name;
    }

}

