package photopolis.loalityprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import photopolis.loalityprogram.model.Statistic;
import photopolis.loalityprogram.service.StatisticsService;

/**
 * Created by Anatoliy on 03.06.2018.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "get/{startDate}/{endDate}",method = RequestMethod.GET)
    private ResponseEntity<Statistic> getStat(@PathVariable String startDate, @PathVariable String endDate){
        if (startDate==null||endDate==null)
            return new ResponseEntity<Statistic>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Statistic>(statisticsService.getStatistic(startDate,endDate),HttpStatus.OK);
    }
}
