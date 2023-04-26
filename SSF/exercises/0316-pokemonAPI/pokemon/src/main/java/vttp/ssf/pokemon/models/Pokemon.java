package vttp.ssf.pokemon.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Pokemon {
    private static String[] IMAGES = {"sprites", "versions", "generation-i", "red-blue"};

    private String name;
    private List<String> images = new LinkedList<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public void addImage(String img) { this.images.add(img); }

    @Override
    public String toString() { return "Pokemon [images=" + images + ", name=" + name + "]"; }

    // sprites > versions > generation-i > red-blue
    public static Pokemon create(String json) throws IOException {
        Pokemon p = new Pokemon();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            p.setName(o.getString("name"));
            /* 
            o = o.getJsonObject("sprites");
            o = o.getJsonObject("versions");
            o = o.getJsonObject("generation-i");
            o = o.getJsonObject("red-blue");
            */
            for (String i: IMAGES)
                o = o.getJsonObject(i);
            
            List<String> l = o.values().stream()
                .filter(v -> v != null)
                .map(v -> v.toString().replaceAll("\"", ""))
                .toList();
            
            p.setImages(l);
        }
        return p;
    }
}
