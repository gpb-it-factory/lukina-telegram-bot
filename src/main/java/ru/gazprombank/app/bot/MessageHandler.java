package ru.gazprombank.app.bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageHandler {
    private UserClient userClient;
    enum MessageType {
        PING,
        REGISTER
    }

    public MessageHandler(UserClient userClient) {
        this.userClient = userClient;
    }

    public String getResponseMessageText(String message) {
        Optional<MessageType> messageTypeOptional = parseMessage(message);
        if (messageTypeOptional.isPresent()) {
            MessageType messageType = messageTypeOptional.get();
            switch (messageType) {
                case PING:
                    return "pong";
                case REGISTER:
                    return this.userClient.register();
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
            default:
                return Optional.empty();
        }
    }
}
