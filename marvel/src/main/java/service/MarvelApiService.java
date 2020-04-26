package service;
import models.Character;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MarvelApiService implements MarvelApiServiceInterface {

    private static final String baseUri = "http://gateway.marvel.com/v1/public/";

    public List<Integer> getCharacterIds() throws Exception {

        String uri = baseUri + "comics?" + generateAuthInfo();
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> ids = new ArrayList<>();

        try {
            Character character = restTemplate.getForObject(uri, Character.class);
            ids.add(character.getId());
        }
        catch (RestClientException e) {
            e.printStackTrace();
        }

        return ids;
    }

    //Method to generate authentication to access the api
    private static String generateAuthInfo() throws Exception {
        String publicKey = "c89f61e9a213cb3bae3a62bdc32dce3c";
        String privateKey = "ed2c46a8dd1915580ff758d8a65893f86c56758e";
        String timeStamp = String.valueOf(new Date().getTime());
        String stringToHash = timeStamp + privateKey + publicKey;

        return "ts=" + timeStamp + "&apikey=" + publicKey + "&hash=" + getMd5(stringToHash);
    }

    //Method to create an MD5 hash value
    private static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
