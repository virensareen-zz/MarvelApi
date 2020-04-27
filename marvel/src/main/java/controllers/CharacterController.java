package controllers;

import models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.LanguageTranslatorService;
import service.MarvelApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/characters/{id}?language={languageCode}")
    public Character TranslateDescription(@PathVariable Integer id, @PathVariable String languageCode) throws Exception {
        String description = getCharacter(id).getDescription();

        LanguageTranslatorService translator = new LanguageTranslatorService();

        return translator.translateInto(description, languageCode);
    }
}
