package photopolis.loalityprogram.service.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.TelegramDispatch;
import photopolis.loalityprogram.service.TelegramDispatchService;

public class Bot extends TelegramLongPollingBot {

    @Autowired
    private TelegramDispatchService telegramDispatchService;

    private static Logger logger=Logger.getLogger(Bot.class);

    @Override
    public void onUpdateReceived(Update update) {
        String message="";
        String chatId = update.getMessage().getChatId().toString();
        if(update.getMessage().getText().equals("/start")){
            telegramDispatchService.save(new TelegramDispatch(chatId));
            message="Dispatch created";
        }
        if (update.getMessage().getText().equals("/EnableDispatch")) {
            telegramDispatchService.setActive(chatId);
            message = "Dispatch activated";
        }
        if (update.getMessage().getText().equals("/DisableDispatch")) {
            telegramDispatchService.setUnactive(chatId);
            message = "dispatch deactivated";
        }
        if (message.equals("")){
            message = "Список команд:\n/EnableDispatch - Включити розсилку\n/DisableDispatch - Відключити розсилку";
        }
        sendMsg(update.getMessage().getChatId().toString(), message);

    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return Constants.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }


}