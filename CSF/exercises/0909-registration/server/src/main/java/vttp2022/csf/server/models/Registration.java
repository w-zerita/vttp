package vttp2022.csf.server.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Registration {
    private String id;
    private String name;
    private String email;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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

    public static Registration create(String json) {

        JsonReader r = Json.createReader(new StringReader(json));
        JsonObject o = r.readObject();

        final Registration reg = new Registration();
        reg.setName(o.getString("name"));
        reg.setEmail(o.getString("email"));
        // since id is optional, check if id is in the payload
        if (o.containsKey("id"))
            reg.setId(o.getString("id"));
        
        return reg;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("email", email)
            .build();
    }
}
