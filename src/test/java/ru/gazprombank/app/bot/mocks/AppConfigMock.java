package ru.gazprombank.app.bot.mocks;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class AppConfigMock {
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(this.timeout);
        factory.setReadTimeout(this.timeout);

        return new RestTemplate(factory);
    }

    private int timeout = 5000;
}
