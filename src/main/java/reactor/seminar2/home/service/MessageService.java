package reactor.seminar2.home.service;

import reactor.utils.Logger;

public class MessageService {

    public void sendMessage(String clientEmail, String message) {
        Logger.log("Client: %s received a message - %s".formatted(clientEmail, message));
    }
}