package ru.gazprombank.app.bot.mocks;

import org.springframework.web.client.RestTemplate;
import ru.gazprombank.app.bot.AccountClient;

public class AccountClientMock extends AccountClient {
    public AccountClientMock(AppConfigMock config) {
        super("", config.restTemplate());
    }
}
