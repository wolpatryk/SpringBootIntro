package pl.kagmajn.writer.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@Slf4j
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON() {
        try {
            Random r = new Random();
            int randomInt = r.nextInt(100) + 1;
//            String url = "https://jsonplaceholder.typicode.com/posts/" + randomInt;
//            String url = "http://localhost:9999/ops/health";
            String url = "http://localhost:9999/ops/status";
//            String url = "http://localhost:8000/get-item/"+randomInt;
            String response = this.restTemplate.getForObject(url, String.class);
            return response;
        } catch (Exception e) {
            return "{\"error\": \"" + e + "\"}";
        }
    }
}
