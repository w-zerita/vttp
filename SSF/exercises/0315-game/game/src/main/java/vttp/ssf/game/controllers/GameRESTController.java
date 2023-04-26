package vttp.ssf.game.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.ssf.game.models.Game;
import vttp.ssf.game.repositories.GameRepository;
import vttp.ssf.game.services.GameService;

@RestController
@RequestMapping(path = "game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRESTController {
    @Autowired
    GameRepository grepo;

    @Autowired
    GameService gameSvc;

    // GET /game/<gid>
    @GetMapping(path="/{gid}")
    ResponseEntity<String> getGame(@PathVariable(name="gid") String gid) {
        /*
        // fast-lane reader design - reading data directly from repo
        String game = (String) grepo.getGameById(gid);
        System.out.println(">>>>> game: " + game);
        return ResponseEntity.ok(game);
        */

        // FORCE developers to check for null
        Optional<Game> opt = grepo.getGameById(gid);
        if (opt.isEmpty()) {
            JsonObject result = Json.createObjectBuilder()
                .add("error", "gid = %s not found".formatted(gid))
                .build();
            return ResponseEntity.status(404).body(result.toString());
        }
        Game game = opt.get();
        return ResponseEntity.ok(game.toJson().toString());
    }

    // GET /game/search?pattern=<path>
    // E.g. /game/search?pattern=3    *3* RedisTemplate keys
    @GetMapping(path="/search")
    ResponseEntity<String> getSearchGame(@RequestParam String pattern) {

        System.out.println(">>>>> pattern: " + pattern);

        Set<String> gids = gameSvc.searchKeys(pattern);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        gids.stream()
            .sorted()
            .map(gid -> {
                return "/game/%s".formatted(gid);
        }).forEach(url -> {
            arrayBuilder.add(url);
        });
        return ResponseEntity.ok(arrayBuilder.build().toString());

        /*
        Set<String> keySet = grepo.getKeys("*" + pattern + "*");
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        keySet.stream()
            .map((String gid) -> {
                String game = (String) grepo.getGameById(gid);
                InputStream is = new ByteArrayInputStream(game.getBytes());
                JsonReader reader = Json.createReader(is);
                return reader.readObject();
            })
            .forEach((JsonObject o) -> {
                arrBuilder.add(o);
            });
        JsonArray gameArray = arrBuilder.build();
        return ResponseEntity.ok(gameArray.toString());
        */

        // Think of how to display game's name in url
    }
    
}
