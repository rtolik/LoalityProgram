package photopolis.loalityprogram.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import photopolis.loalityprogram.model.CelebrateDate;
import photopolis.loalityprogram.service.BonusService;
import photopolis.loalityprogram.service.CelebrationDateService;
import photopolis.loalityprogram.service.TelegramBotService;
import photopolis.loalityprogram.service.TelegramDispatchService;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static photopolis.loalityprogram.service.utils.Utility.dataEqualiser;
import static photopolis.loalityprogram.service.utils.Utility.dataParser;

/**
 * Created by Anatoliy on 05.06.2018.
 */
@RestController
@RequestMapping("/celebration-date")
public class CelebrationDateController {

    Logger logger=Logger.getLogger(CelebrationDateController.class);

    @Autowired
    private CelebrationDateService celebrationDateService;

    @Autowired
    private BonusService bonusService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    private ResponseEntity<CelebrateDate> add(@RequestParam String date, @RequestParam String name,
                                              @RequestParam Double bonuses){
        if(date==null||name==null||bonuses==null)
            return new ResponseEntity<CelebrateDate>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<CelebrateDate>(
                celebrationDateService.save(new CelebrateDate(name,dataParser(date),bonuses))
                ,HttpStatus.OK
        );
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    private ResponseEntity delete(@PathVariable Integer id){
        if(id==null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        celebrationDateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/find-all",method = RequestMethod.GET)
    private ResponseEntity<List<CelebrateDate>> findAll(){
        return new ResponseEntity<List<CelebrateDate>>(celebrationDateService.findAll(),HttpStatus.OK);
    }

    @Scheduled(fixedDelay = 86400000)//1 day
//    @Scheduled(fixedDelay = 60000)//1 minute
    private void countBonuses(){
        celebrationDateService.sendCelebrationDateInfoToTelegram();
        logger.info("Checked");
    }

}
