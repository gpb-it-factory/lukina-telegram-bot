package ru.gazprombank.app.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private String botName;
    private String token;
    private MessageHandler messageHandler;

    public TelegramBot(@Value("${bot.name}") String botName, @Value("${bot.token}") String token) {
        super();
        this.messageHandler = new MessageHandler();
        this.botName = botName;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("=========== onUpdateReceived =================");
        String updateMessageText = update.getMessage().getText();
        log.info("update с сообщением: {}", updateMessageText);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String responseMessageText = this.messageHandler.getResponseMessageText(update.getMessage().getText());
        log.info("текст ответа: {}", responseMessageText);
        sendMessage.setText(responseMessageText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("ошибка отправки сообщения: {}", e.getMessage());
        }
    }
}
