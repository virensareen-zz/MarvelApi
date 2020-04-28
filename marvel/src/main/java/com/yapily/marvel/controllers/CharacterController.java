package com.yapily.marvel.controllers;

import libraries.GoogleTranslate;
import com.yapily.marvel.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.yapily.marvel.service.MarvelApiService;

import java.util.List;

@RestController
@RequestMapping
public class CharacterController {

    @Autowired
    private RestTemplate restTemplate;
    private MarvelApiService marvelApiService = new MarvelApiService();

    @GetMapping("/characters")
    public List<Integer> getCharacterIds() throws Exception {
       return marvelApiService.getCharacterIds();
    }

    @GetMapping("/characters/{id}")
    public Character getCharacter(@PathVariable Integer id, @RequestParam(required = false) String language) throws Exception {
        Character character = marvelApiService.getCharacter(id);

        String translatedDescription = GoogleTranslate.translate(language, character.getDescription());
        character.setDescription(translatedDescription);

        return character;
    }
}
