package vttp.ssf.game.services;

import java.io.InputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import vttp.ssf.game.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    // biz decisions done in service layer to keep repo generic (deal with data alone)

    public Set<String> searchKeys(String index) {
        String pattern = "*%s*".formatted(index);
        return gameRepo.getKeys(pattern);
    }
    
    public JsonArray loadData(InputStream is) {
        JsonReader r = Json.createReader(is);
        return r.readArray();
    }
}
