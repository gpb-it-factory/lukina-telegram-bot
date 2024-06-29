package ru.gazprombank.app.bot.mocks;

import org.springframework.web.client.RestTemplate;
import ru.gazprombank.app.bot.UserClient;

public class UserClientMock extends UserClient {
    public UserClientMock(AppConfigMock config) {
        super("", config.restTemplate());
    }
    @Override
    public String register(Long telegramUserId, String telegramUserName) {
        this.registerUserRequestBody = new RegisterUserRequestBody(telegramUserId, telegramUserName);
        return telegramUserId.toString() + telegramUserName;
    }

    public RegisterUserRequestBody getLastRegisterArgs() {
        return this.registerUserRequestBody;
    }

    public void cleanup() {
        this.registerUserRequestBody = null;
    }

    private RegisterUserRequestBody registerUserRequestBody;
    public record RegisterUserRequestBody(Long telegram_user_id, String telegram_user_name) {}
}
