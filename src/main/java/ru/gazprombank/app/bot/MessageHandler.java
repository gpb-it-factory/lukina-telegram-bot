package ru.gazprombank.app.bot;
import java.util.Optional;

public class MessageHandler {
    enum MessageType {
        PING
    }
    public String getResponseMessageText(String message) {
        Optional<MessageType> messageTypeOptional = parseMessage(message);
        if (messageTypeOptional.isPresent()) {
            MessageType messageType = messageTypeOptional.get();
            switch (messageType) {
                case PING:
                    return "pong";
            }
        }
        return "pong";
    }

    private Optional<MessageType> parseMessage(String message) {
        String messageType = message.trim().toUpperCase();
        switch (messageType) {
            case "PING":
                return Optional.of(MessageType.PING);
            default:
                return Optional.empty();
        }
    }
}
