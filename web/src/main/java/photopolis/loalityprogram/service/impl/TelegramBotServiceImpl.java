package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.config.Constants;
import photopolis.loalityprogram.model.TelegramDispatch;
import photopolis.loalityprogram.service.TelegramBotService;
import photopolis.loalityprogram.service.TelegramDispatchService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Anatoliy on 19.06.2018.
 */
@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    @Autowired
    private TelegramDispatchService telegramDispatchService;

    @Override
    public void sendMessage(String message) {
        try {
            List<TelegramDispatch> activeUsers= telegramDispatchService.findAllActive();
            for (TelegramDispatch user:activeUsers) {
                String query = Constants.BOT_SENDMESSAGE_URL
                        + "chat_id=" + user.getChatId() +
                        "&text=" + URLEncoder.encode(message, "UTF-8");
                URL obj = new URL(query);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                connection.setDoOutput(true);
                String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
