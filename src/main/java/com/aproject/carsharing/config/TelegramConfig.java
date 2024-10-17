package com.aproject.carsharing.config;

import com.aproject.carsharing.service.notification.impl.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramConfig {
    private static final Logger log = LoggerFactory.getLogger(TelegramConfig.class);
    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Bean
    public TelegramBot getTelegramBot() {
        return new TelegramBot(botToken, botUsername);
    }

    @Bean
    public TelegramBotsApi getTelegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
        if (botToken.isEmpty() || botUsername.isEmpty()) {
            log.warn("Telegram bot token or username is empty. Telegram bot won't be initialized.");
            return null;
        }
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramBot);
        return botsApi;
    }
}
