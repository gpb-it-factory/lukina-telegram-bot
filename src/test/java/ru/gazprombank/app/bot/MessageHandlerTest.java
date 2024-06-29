package ru.gazprombank.app.bot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gazprombank.app.bot.mocks.AccountClientMock;
import ru.gazprombank.app.bot.mocks.AppConfigMock;
import ru.gazprombank.app.bot.mocks.UserClientMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageHandlerTest {
    private AppConfigMock config = new AppConfigMock();
    @Test
    public void testGetResponseMessageText() {
        UserClientMock userClientMock = new UserClientMock(this.config);
        AccountClientMock accountClientMock = new AccountClientMock(this.config);
        MessageHandler messageHandler = new MessageHandler(userClientMock, accountClientMock);
        Long test_user_id = 1684L;
        String test_user_name = "joeblack";
        String expected = userClientMock.register(test_user_id, test_user_name);

        String actual = messageHandler.getResponseMessageText(test_user_id, test_user_name, "register");

        assertEquals(expected, actual);
    }
}
