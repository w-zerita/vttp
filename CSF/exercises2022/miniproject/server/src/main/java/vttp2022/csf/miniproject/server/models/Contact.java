package vttp2022.csf.miniproject.server.models;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Contact implements Serializable {
    private String name;
    private String email;
    private String mobile;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static Contact create(String json) {

        JsonReader r = Json.createReader(new StringReader(json));
        JsonObject o = r.readObject();
        final Contact c = new Contact();
        c.setName(o.getString("name"));
        c.setEmail(o.getString("email"));
        c.setMobile(o.getString("mobile"));

        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", name)
            .add("email", email)
            .add("mobile", mobile)
            .build();
    }
}
