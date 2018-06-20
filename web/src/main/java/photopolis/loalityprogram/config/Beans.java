package photopolis.loalityprogram.config;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import photopolis.loalityprogram.service.utils.Bot;

@Component
@Configuration
public class Beans {

    private static Logger logger= Logger.getLogger(Beans.class);

    @Bean
    public TelegramLongPollingBot bot(){
        ApiContextInitializer.init();
        TelegramLongPollingBot bot= new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(bot);
            return bot;
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        logger.error("Error creating bot");
        return bot();
    }
}
