package ru.gazprombank.app.bot;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class UserClient {
    private String host;
    private RestTemplate restTemplate;

    @Autowired
    public UserClient(@Value("${middle_service.host}") String host, RestTemplate restTemplate) {
        super();
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public String register(Long telegramUserId, String telegramUserName) {
        String url = this.host + "/users";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("telegram_user_id", telegramUserId);
        jsonObject.put("telegram_user_name", telegramUserName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity(jsonObject.toString(), headers);
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println("Response from register: " + response);
        return response;
    }
}
