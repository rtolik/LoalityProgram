package photopolis.loalityprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photopolis.loalityprogram.model.CelebrateDate;
import photopolis.loalityprogram.repository.CelebrateDateRepository;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.CelebrationDateService;
import photopolis.loalityprogram.service.TelegramBotService;
import photopolis.loalityprogram.service.TelegramDispatchService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.dataEqualiser;
import static photopolis.loalityprogram.service.utils.Utility.dataParser;

/**
 * Created by Anatoliy on 05.06.2018.
 */
@Service
public class CelebrationDateServiceImpl implements CelebrationDateService{

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private CelebrateDateRepository celebrateDateRepository;

    @Override
    public CelebrateDate save(CelebrateDate date) {
        return celebrateDateRepository.save(date);
    }

    @Override
    public void delete(Integer id) {
        celebrateDateRepository.delete(id);
    }

    @Override
    public List<CelebrateDate> findAll() {
        return celebrateDateRepository.findAll();
    }

    @Override
    public String setAndGetUsersWithCelebrationDate() {
        String message="";
        List<CelebrateDate> dates= new ArrayList<>();
        dates=findAll().stream().filter(date ->
            dataEqualiser(dataParser(LocalDate.now().toString()),date.getDate()))
                .collect(toList());
        System.out.println(dates.get(0).getId()+" "+dates.get(0).getName());
        if (!dates.isEmpty()) {
            bonusService.setPartyBonus(dates);
            message="Всім користувачам були нараховані бонуси за ";
            for (CelebrateDate date:dates) {
                System.out.println(date.getDate()+" "+date.getId());
                message+=date.getName()+" ";
            }
        }
        String tmp=bonusService.setAnniversaryBonus();
        if (tmp.length()>0)
            message+="За річницю отримання карти, бонуси отримали: "+tmp;
        tmp=bonusService.setBirhDayBonus();
        if (tmp.length()>0)
            message+="На честь дня народження, бонуси нараховано: "+tmp;
        return message;
    }

    @Override
    public void sendCelebrationDateInfoToTelegram() {
        String message=setAndGetUsersWithCelebrationDate();
        if (message.length()>0)
            telegramBotService.sendMessage(message);
    }
}
