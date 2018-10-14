package photopolis.loalityprogram.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.TelegramDispatch;
import photopolis.loalityprogram.model.enums.Role;
import photopolis.loalityprogram.repository.TelegramDispatchRepository;
import photopolis.loalityprogram.service.TelegramDispatchService;
import photopolis.loalityprogram.service.UserService;
import photopolis.loalityprogram.service.utils.Bot;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anatoliy on 20.06.2018.
 */
@Service
public class TelegramDispatchServiceImpl implements TelegramDispatchService {

    @Autowired
    private UserService userService;

    @Autowired
    private TelegramDispatchRepository telegramDispatchRepository;

    private  static Logger logger = Logger.getLogger(TelegramDispatchServiceImpl.class);



    @Override
    public void save(TelegramDispatch telegramDispatch) {
        telegramDispatchRepository.save(telegramDispatch);
    }

    @Override
    public void delete(Integer id) {
        telegramDispatchRepository.delete(id);
    }

    @Override
    public void deleteByChatId(String chatId) {
        delete(findOneByChatId(chatId).getId());
    }

    @Override
    public TelegramDispatch findOneByChatId(String chatId) {
        return findAll()
                .stream()
                .filter(telegramDispatch -> telegramDispatch.getChatId().equals(chatId))
                .findFirst().get();
    }

    @Override
    public List<TelegramDispatch> findAll() {
        return telegramDispatchRepository.findAll();
    }

    @Override
    public List<TelegramDispatch> findAllActive() {
        return findAll().stream().filter(TelegramDispatch::getActive).collect(toList());
    }

    @Override
    public TelegramDispatch findByUserCardId(Integer cardId) {
        return findAll().stream().filter(telegramDispatch -> telegramDispatch.getUser().getCardId()==cardId).findFirst().get();
    }

    @Override
    public TelegramDispatch findOne(Integer id) {
        return telegramDispatchRepository.findOne(id);
    }

    @Override
    public void setUnactive(String chatId) {
        save(findOneByChatId(chatId).setActive(false));
    }

    @Override
    public void setActive(String chatId) {
        save(findOneByChatId(chatId).setActive(true));
    }

    @Override
    public void changeRole(Integer cardId) {
        List<TelegramDispatch> dispatches =
                findAll()
                        .stream()
                        .filter(telegramDispatch -> telegramDispatch.getUser().getCardId()==cardId).collect(toList());
        for (TelegramDispatch d :
                dispatches) {
            save(d.setRole(Role.ADMIN));
        }

    }
}
