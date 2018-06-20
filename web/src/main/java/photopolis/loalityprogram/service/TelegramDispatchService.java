package photopolis.loalityprogram.service;

import photopolis.loalityprogram.model.TelegramDispatch;

import java.util.List;

/**
 * Created by Anatoliy on 20.06.2018.
 */
public interface TelegramDispatchService {
    void save(TelegramDispatch telegramDispatch);

    void delete(Integer id);

    void deleteByChatId(String chatId);

    List<TelegramDispatch> findAll();

    TelegramDispatch findOne(Integer id);

    TelegramDispatch findOneByChatId(String chatId);

    void setUnactive(String chatId);

    void setActive(String chatId);

    List<TelegramDispatch> findAllActive();

}
