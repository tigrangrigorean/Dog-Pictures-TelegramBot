package com.dog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramService extends TelegramLongPollingBot{
	
	
	@Autowired
	private DogApiService dogApiService;
	
	
	private String apiKey = "6672399097:AAFD5y8xTVaLZVZuUGEfP2HOXbxSgF04L-s";
	private String username = "doggy_pictures_bot";

	@Override
	public void onUpdateReceived(Update update) {
		  if (update.hasMessage() && update.getMessage().hasText()) {
	            String messageText = update.getMessage().getText();
	            if ("/start".equals(messageText)) {
	                String chatId = update.getMessage().getChatId().toString();
	                sendMessageToChat(chatId,"Hi just write /dog and I will send you a photo of the dog");
	                
	            }
	            if ("/dog".equals(messageText)) {
	                String chatId = update.getMessage().getChatId().toString();
	                sendMessageToChat(chatId,"Wow, here comes the dog :)");
	                sendPhoto(chatId);
	            }
	        }
	}
	
	
	public void menuButton(SendPhoto sendPhoto) {
		 ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	        List<KeyboardRow> keyboard = new ArrayList<>();
	        KeyboardRow row = new KeyboardRow();
	        row.add("/dog");
	        keyboard.add(row);
	        keyboardMarkup.setKeyboard(keyboard);
	        sendPhoto.setReplyMarkup(keyboardMarkup);
	}
	
	public void menuButton(SendMessage sendMessage) {
		 ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	        List<KeyboardRow> keyboard = new ArrayList<>();
	        KeyboardRow row = new KeyboardRow();
	        row.add("/dog");
	        keyboard.add(row);
	        keyboardMarkup.setKeyboard(keyboard);
	        sendMessage.setReplyMarkup(keyboardMarkup);
	}
	
	public void sendPhoto(String chatId) {
		  try {
		        SendPhoto sendPhoto = new SendPhoto();
		        sendPhoto.setChatId(chatId);
		        sendPhoto.setPhoto(new InputFile(dogApiService.getUrl()));
		        menuButton(sendPhoto);
		        execute(sendPhoto);
		    } catch (TelegramApiException e) {
		        e.printStackTrace();
		    }
	}
	
	   public void sendMessageToChat(String chatId, String message) {
	        SendMessage sendMessage = new SendMessage();
	        sendMessage.setChatId(chatId);
	        sendMessage.setText(message);
	        menuButton(sendMessage);
	        try {
	            execute(sendMessage);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public String getBotToken() {
		return apiKey;
	}

}
