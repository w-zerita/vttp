package vttp2022.csf.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/starwars")
public class StarWarsRESTController {
    
    @GetMapping
    public ResponseEntity<String> getCategories() {
        return null;
    }
}
