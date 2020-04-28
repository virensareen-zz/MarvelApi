package com.yapily.marvel.service;
import com.yapily.marvel.models.Character;
import com.yapily.marvel.models.MarvelResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MarvelApiService {

    private static final String baseUri = "http://gateway.marvel.com/v1/public/";

    public List<Integer> getCharacterIds() throws Exception {

        String uri = baseUri + "characters?" + generateAuthInfo();
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> ids = new ArrayList<>();

        try {
            MarvelResponse response = new MarvelResponse();
            response = restTemplate.getForObject(uri, MarvelResponse.class);

            for (Character character : response.data.results) {
                ids.add(character.getId());
            }
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }

        return ids;
    }

    public Character getCharacter(Integer id) throws Exception {
        String uri = baseUri + "characters/" + id + "?" + generateAuthInfo();
        RestTemplate restTemplate = new RestTemplate();
        Character character = new Character();

        try {
            MarvelResponse response = new MarvelResponse();
            response = restTemplate.getForObject(uri, MarvelResponse.class);

            character = response.data.results.get(0);
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }

        return character;
    }

    //Method to generate authentication to access the api
    private static String generateAuthInfo() throws Exception {
        String publicKey = System.getenv("PUBLIC_KEY");
        String privateKey = System.getenv("PRIVATE_KEY");
        String timeStamp = String.valueOf(new Date().getTime());
        String stringToHash = timeStamp + privateKey + publicKey;

        return "ts=" + timeStamp + "&apikey=" + publicKey + "&hash=" + getMd5(stringToHash);
    }

    //Method to create an MD5 digest
    private static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = DigestUtils.md5Digest(input.getBytes("UTF-8"));

            // Convert to string
            return toHexString(messageDigest);
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHexString(byte[] digest) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
