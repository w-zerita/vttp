package vttp.ssf.game.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.ssf.game.models.Game;

@Repository
public class GameRepository {
    @Autowired
    @Qualifier("games") // look for "games"
    private RedisTemplate<String, String> redisTemplate;

    public void save(JsonObject obj) {
        Integer gid = obj.getInt("gid");
        redisTemplate.opsForHash() // key, hashKey, value
            .put("%d".formatted(gid), "rec", obj.toString());
    }

    // search function no biz logic, don't need to use service
    // return String for easy manipulation of data, platform neutral

    public Optional<Game> getGameById(String gid) {
        String rec = (String) redisTemplate.opsForHash().get(gid, "rec");
    
        if (null != rec) {
            return Optional.of(Game.create(rec)); // return game in a box
        }
        return Optional.empty(); // nip bud at the source
    }

    public Set<String> getKeys(String key) {
        return redisTemplate.keys(key);
    }
}
