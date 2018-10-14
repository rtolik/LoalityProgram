package photopolis.loalityprogram.service.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.Bonus;
import photopolis.loalityprogram.model.TelegramDispatch;
import photopolis.loalityprogram.model.User;
import photopolis.loalityprogram.model.enums.Role;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.TelegramDispatchService;
import photopolis.loalityprogram.service.UserService;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    @Autowired
    private UserService userService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private TelegramDispatchService telegramDispatchService;

    private static Logger logger=Logger.getLogger(Bot.class);

    @Override
    public void onUpdateReceived(Update update) {
        String message = "";
        String chatId = update.getMessage().getChatId().toString();
        String updateText = update.getMessage().getText();
        if (updateText.equals("/start")) {
            message = "Привіт, я бот фотостудії Photopolis, твій помічник!" +
                    "\nЯкщо це наша перша зустріч, напиши мені: \n\"Привіт, номер своєї картки\"." +
                    "\nЯкщо ми вже спілкувались, \n\"Вхід, номер своєї карти лояльності і пароль " +
                    "(номер карти * число дати твого народження)\". " +
                    "\nВ одному повідомленні через пробіл" +
                    "\nДля отримання списку доступних команд, надішли мені \"Команди\"" ;
        }
        if (updateText.contains("Привіт")|| updateText.contains("привіт")) {
            Integer cardId = Integer.parseInt(updateText.split(" ")[1]);

            if (userService.findByCardId(cardId)!=null) {
                String password = Integer.parseInt(userService.findByCardId(cardId).getDateOfBirth().split("-")[2]) * cardId + "";


                telegramDispatchService.save(new TelegramDispatch(update.getMessage().getChatId().toString(),
                        userService.findByCardId(cardId), password));
                message = "Вітаю, тепер ти першим будеш отримувати спеціальні пропозиції та новини.\nЩоб відмовитись " +
                        "від отримання спеціальних пропозицій, відправ мені текст: \"Відмовитись від розсилки\".\n" +
                        "Ти завжди зможеш повернутись, відправивши мені текст: \"Отримувати новини\".\n" +
                        "Щоб дізнатись кількість доступних бонусів, та їх термін дії, Надішли мені слово: \"Бонуси\"." +
                        "Зв'язатись з нами можеш написавши адміністратору " + Constants.BOT_PHOTOPOLIS_ADMIN;
            }
            else {
                message="Користувача з такою карткою не існує! впевнись в правильності номера картки, або зв'яжись" +
                        " з адміністратором "+Constants.BOT_PHOTOPOLIS_ADMIN;
            }
        }

        if (updateText.contains("Вхід") || updateText.contains("вхід")) {
            Integer cardId = Integer.parseInt(updateText.split(" ")[1]);
            String password = updateText.split(" ")[2];
            if (userService.findByCardId(cardId)!=null) {
                if (password.equals(telegramDispatchService.findByUserCardId(cardId).getPassword())) {
                    telegramDispatchService.save(new TelegramDispatch(update.getMessage().getChatId().toString(),
                            userService.findByCardId(cardId), password));
                    message = "Вітаю, тепер ти першим будеш отримувати спеціальні пропозиції та новини.\nЩоб відмовитись " +
                            "від отримання спеціальних пропозицій, відправ мені текст: \"Відмовитись від розсилки\".\n" +
                            "Ти завжди зможеш повернутись, відправивши мені текст: \"Отримувати новини\".\n" +
                            "Щоб дізнатись кількість доступних бонусів, та їх термін дії, Надішли мені слово: \"Бонуси\"." +
                            "Зв'язатись з нами можеш написавши адміністратору " + Constants.BOT_PHOTOPOLIS_ADMIN;
                } else {
                    message = "Пароль невірний. Спробуй ще раз.";
                }
            }
            else {
                message="Користувача з такою карткою не існує! впевнись в правильності номера картки, або зв'яжись" +
                        " з адміністратором "+Constants.BOT_PHOTOPOLIS_ADMIN;
            }
        }
        if (updateText.contains("Відмовитись від розсилки") || updateText.contains("відмовитись від розсилки")) {
            telegramDispatchService.setUnactive(chatId);
            message = "Тепер ти не будеш отримувати новини та спеціальні пропозиції.\n" +
                    "Щоб знову отримувати пропозиції відправ: \"Отримувати новини\"";
        }
        if (updateText.contains("Отримувати новини")||updateText.contains("отримувати новини")) {
            telegramDispatchService.setActive(chatId);
            message = "Вітаю, тепер ти знову будеш першим отримувати новини та спеціальні пропозиції.";
        }

        if (updateText.equals("Бонуси")||updateText.equals("бонуси")){
            User user= telegramDispatchService.findOneByChatId(update.getMessage().getChatId().toString()).getUser();
            String bonusInfo="";
            List<Bonus> bonuses=bonusService.findAllByUserId(user.getId());
            for (Bonus b : bonuses) {
                if (b.getValue()!=0)
                    bonusInfo+= b.getValue()+" бонусів, дійсних до "+b.getDateOfEnd()+"\n";
            }
            message="У вас є: "+bonusInfo;
        }

        if (updateText.contains("/signUpAdmin")||updateText.contains("/SignUpAdmin")) {
            logger.info("In");
            Integer cardId = Integer.parseInt(updateText.split(" ")[1]);
            if (userService.findByCardId(cardId)!=null) {
                String password = updateText.split(" ")[2];
                if (password.equals(Constants.PASSWORD)) {
                    logger.info("Checked");
                    telegramDispatchService.changeRole(cardId);
                    message = "Красаучік Уаса!!!! Ти в матриці, Нео";
                } else {
                    logger.info("Loh");
                    message = "AHAHAHAHAHAHAHHA LOH!!!! SOSAT PIDOR";
                }
            }
            else {
                message="Мимо! Може спробуєш сходити на В5?";
            }
        }
        if (updateText.contains("/sendAll")||updateText.contains("/SendAll")) {
            TelegramDispatch user=telegramDispatchService.findOneByChatId(update.getMessage().getChatId().toString());
            if (user.getRole().equals(Role.ADMIN)) {
                message = updateText.substring(8);
                List<TelegramDispatch> all = telegramDispatchService.findAll();
                String finalMessage = message;
                all.forEach(telegramDispatch -> sendMsg(telegramDispatch.getChatId(), finalMessage));
                message = "Успішно надіслано";
            }
            else {
                message="Ахахахахах, допитливий пісюнець, гарна спроба";
            }
        }

        if (updateText.equals("Команди")){
            message = "Список команд:\n" +
                    "\"Вхід  номер своєї карти лояльності і пароль (номер карти * число дати твого народження)\"" +
                    " - вхід у систему (можна використовувати для кількох акаунтів телеграму)" +
                    "\n\"Привіт номер своєї картки\" - якщо це наша перша зустріч " +
                    "\n\"Бонуси\" - дізнатись кількість доступних бонусів, та їх термін дії" +
                    "\n\"Відмовитись від розсилки\" - відмовитись від отримання спеціальних пропозицій та новин" +
                    "\n\"Отримувати новини\" - відновити отримання спеціальних пропозицій та новин"+
                    "\nЗв'язатись з нами можеш написавши адміністратору " + Constants.BOT_PHOTOPOLIS_ADMIN;
        }
        sendMsg(chatId, message);

    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
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