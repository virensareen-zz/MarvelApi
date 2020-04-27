package controllers;

import libraries.GoogleTranslate;
import models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.MarvelApiService;

import java.util.List;

@RestController
@RequestMapping("")
public class CharacterController {

    @Autowired
    private RestTemplate restTemplate;
    private MarvelApiService marvelApiService = new MarvelApiService();

    @GetMapping("/characters")
    public List<Integer> getCharacterIds() throws Exception {
       return marvelApiService.getCharacterIds();
    }

    @GetMapping("/characters/{id}")
    public Character getCharacter(@PathVariable Integer id) throws Exception {
        return marvelApiService.getCharacter(id);
    }
}
