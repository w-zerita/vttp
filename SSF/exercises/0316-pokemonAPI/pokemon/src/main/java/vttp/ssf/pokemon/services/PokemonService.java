package vttp.ssf.pokemon.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp.ssf.pokemon.models.Pokemon;

@Service
public class PokemonService {

    private static final String URL = "https://pokeapi.co/api/v2/pokemon/%s";
    
    public Optional<Pokemon> findPokemon(String name) {
        String toSearch = URL.formatted(name);

        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> resp = template.getForEntity(toSearch, String.class);
            Pokemon p = Pokemon.create(resp.getBody());
            return Optional.of(p);
            // pokemon exception ~ "gotta catch them all"
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }
}
