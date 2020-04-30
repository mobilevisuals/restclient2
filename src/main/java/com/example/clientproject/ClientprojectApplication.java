package com.example.clientproject;

import com.example.clientproject.models.Address;
import com.example.clientproject.models.Place;
import com.example.clientproject.models.Quote;
import com.example.clientproject.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ClientprojectApplication {

    private static final Logger log = LoggerFactory.getLogger(ClientprojectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientprojectApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            // Quote quote=restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random",Quote.class);
            //log.info(quote.toString());
           // postPlace(restTemplate);
            //placeList(restTemplate);
            pathParamUser(restTemplate);


        };
    }

    private void quoteTest(RestTemplate restTemplate) {
        Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }

    private void pathparamtest(RestTemplate restTemplate) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", 93l);
        Place place = restTemplate.getForObject("http://jsonplaceholder.typicode.com/posts/{id}", Place.class, params);
        log.info(place.toString());
    }

    private void postPlace(RestTemplate restTemplate) {
        final String uri = "http://jsonplaceholder.typicode.com/posts";
        Place place = new Place(111, 112, "test", "body");
        Place object = restTemplate.postForObject(uri, place, Place.class);
        log.info(object.toString());
    }

    private void placeList(RestTemplate restTemplate)
    {
        final String uri = "http://jsonplaceholder.typicode.com/posts";
        Place[] places = restTemplate.getForObject(uri, Place[].class);
        List<Place> placeList= Arrays.asList(places);
        for (Place place:placeList
             ) {
            log.info(place.toString());
        }
    }

    private void pathParamUser(RestTemplate restTemplate) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", 2l);
        User place = restTemplate.getForObject("http://jsonplaceholder.typicode.com/users/{id}", User.class, params);
        log.info(place.toString());
    }

}
