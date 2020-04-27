package controllers;

import libraries.GoogleTranslate;
import models.Character;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters/{id}")
public class LanguageController {

    @RequestMapping
    public Character TranslateDescription(@RequestParam("language") String languageCode) throws Exception {

        Character character = getCharacter(id);
        String translatedDescription = GoogleTranslate.translate(languageCode, character.getDescription());
        character.setDescription(translatedDescription);

        return character;
    }
}
