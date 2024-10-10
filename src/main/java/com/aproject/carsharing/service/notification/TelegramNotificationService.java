package com.aproject.carsharing.service.notification;

public interface TelegramNotificationService {
    void sendNotification(Long chatId, String message);
}
