package ru.gazprombank.app.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class UserClient {
    private String host;
    private RestTemplate restTemplate;

    public UserClient(@Value("${middle_service.host}") String host) {
        super();
        this.host = host;
        this.restTemplate = new RestTemplate();
    }

    public String register() {
        String url = this.host + "/users";
        String response = restTemplate.postForObject(url, null, String.class);
        System.out.println("Response from register: " + response);
        return response;
    }
}
