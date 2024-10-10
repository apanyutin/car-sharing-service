package com.aproject.carsharing.service.notification.impl;

import com.aproject.carsharing.exception.NotificationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Getter
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new NotificationException(
                    String.format("Can't send message: %s by telegram", message));
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/getchatid")) {
            Long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText("Your Chat ID: " + chatId);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new NotificationException("Can't send telegram chatId to user");
            }
        }
    }
}
