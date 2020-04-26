package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.MarvelApiService;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private RestTemplate restTemplate;
    private MarvelApiService marvelApiService = new MarvelApiService();

    @GetMapping("/")
    public List<Integer> getCharacterIds() throws Exception {
       return marvelApiService.getCharacterIds();
    }
}
