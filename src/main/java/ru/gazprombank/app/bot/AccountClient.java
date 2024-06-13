package ru.gazprombank.app.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

@Slf4j
@Component
public class AccountClient {
    private String host;
    private RestTemplate restTemplate;

    @Autowired
    public AccountClient(@Value("${middle_service.host}") String host, RestTemplate restTemplate) {
        super();
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public String createAccount(Long telegramUserId) {
//        String url = this.host + "/accounts/" + telegramUserId + "/accounts";
//        String response = restTemplate.postForObject(url, null, String.class);
//        System.out.println("Response from createAccount: " + response);
//        return response;
        return "NOT IMPLEMENTED";
    }
}
