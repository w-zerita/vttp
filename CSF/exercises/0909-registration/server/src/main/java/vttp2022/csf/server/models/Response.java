package vttp2022.csf.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Response {
    private Integer code = 0;
    private String message = "";
    private String data = "{}";

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("code", code)
            .add("message", message)
            .add("data", data)
            .build();
    }
}
