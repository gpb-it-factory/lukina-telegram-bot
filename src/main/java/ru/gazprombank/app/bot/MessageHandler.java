package ru.gazprombank.app.bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageHandler {
    private UserClient userClient;
    private AccountClient accountClient;
    enum MessageType {
        PING,
        REGISTER,
        CREATE_ACCOUNT
    }

    public MessageHandler(UserClient userClient, AccountClient accountClient) {
        this.userClient = userClient;
        this.accountClient = accountClient;
    }

    public String getResponseMessageText(Long telegramUserId, String telegramUserName, String message) {
        Optional<MessageType> messageTypeOptional = parseMessage(message);
        if (messageTypeOptional.isPresent()) {
            MessageType messageType = messageTypeOptional.get();
            switch (messageType) {
                case PING:
                    return "pong";
                case REGISTER:
                    return this.userClient.register(telegramUserId, telegramUserName);
                case CREATE_ACCOUNT:
                    return "not implemented yet";
            }
        }
        return "pong";
    }

    private Optional<MessageType> parseMessage(String message) {
        String messageType = message.trim().toUpperCase();
        switch (messageType) {
            case "PING":
                return Optional.of(MessageType.PING);
            case "REGISTER":
                return Optional.of(MessageType.REGISTER);
            case "CREATEACCOUNT":
                return Optional.of(MessageType.CREATE_ACCOUNT);
            default:
                return Optional.empty();
        }
    }
}
