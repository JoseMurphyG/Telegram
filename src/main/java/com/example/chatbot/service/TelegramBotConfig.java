package com.example.chatbot.service;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotConfig extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "P2TelegramBot"; // Pon el nombre de tu bot aquí
    }

    @Override
    public String getBotToken() {
        return "7730185683:AAHZy_b_DoCM4uNi8P6Ug5LqOik8zd8C7Zw"; // Coloca el token generado por BotFather
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Procesar el mensaje y enviar una respuesta
            sendMessage(chatId, "Has dicho: " + messageText);
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message); // Enviar el mensaje a Telegram
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotConfig());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

