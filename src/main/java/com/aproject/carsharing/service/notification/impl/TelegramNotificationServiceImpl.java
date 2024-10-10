package com.aproject.carsharing.service.notification.impl;

import com.aproject.carsharing.service.notification.TelegramNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramNotificationServiceImpl implements TelegramNotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void sendNotification(Long chatId, String message) {
        telegramBot.sendMessage(chatId, message);
    }
}
