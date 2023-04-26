package vttp.ssf.pokemon.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.pokemon.models.Pokemon;
import vttp.ssf.pokemon.services.PokemonService;

@Controller
@RequestMapping(path = "pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonService pSvc;

    @GetMapping(path="/search")
    public String getPokemon(@RequestParam String pokemon_name, Model model) {
        Optional<Pokemon> p = pSvc.findPokemon(pokemon_name.toLowerCase());
        if (p.isEmpty()) {
            model.addAttribute("errMsg", "Pokemon not Found!");
            return "error";
        }
        model.addAttribute("search", pokemon_name);
        model.addAttribute("photos", p.get().getImages());
        return "searchResults";
    }
}
