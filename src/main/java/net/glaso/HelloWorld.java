package net.glaso;

import org.json.JSONObject;

public class HelloWorld {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("foo", "bar");
        System.out.println("Hello World");
        System.out.println(json);

    }
}
