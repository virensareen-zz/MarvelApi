package service;
import models.Character;
import models.MarvelResponse;
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
        String publicKey = "c89f61e9a213cb3bae3a62bdc32dce3c";
        String privateKey = "ed2c46a8dd1915580ff758d8a65893f86c56758e";
        String timeStamp = String.valueOf(new Date().getTime());
        String stringToHash = timeStamp + privateKey + publicKey;

        return "ts=" + timeStamp + "&apikey=" + publicKey + "&hash=" + getMd5(stringToHash);
    }

    //Method to create an MD5 digest
    private static String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
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
