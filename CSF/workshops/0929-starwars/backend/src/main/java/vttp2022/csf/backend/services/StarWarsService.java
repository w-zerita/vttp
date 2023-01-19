package vttp2022.csf.backend.services;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsService {

    private static final String BASE_URL = "http https://swapi.dev/api/";
    
    public void getCategories() {

        // configure the GET invocation
        RequestEntity<Void> req = RequestEntity
            .get(BASE_URL)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        // create an instance of RestTemplate
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        // get status code and payload of the request
        System.out.printf("Status code: %d\n", resp.getStatusCodeValue());
        System.out.printf("Payload: %s\n", resp.getBody());
    }
}
