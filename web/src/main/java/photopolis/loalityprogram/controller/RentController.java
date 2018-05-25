package photopolis.loalityprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import photopolis.loalityprogram.DTO.DailyRentDTO;
import photopolis.loalityprogram.service.RentService;

import java.util.List;

/**
 * Created by Anatoliy on 25.05.2018.
 */
@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;


    @RequestMapping(value = "/get-all-by-date",method = RequestMethod.GET)
    private ResponseEntity<List<DailyRentDTO>> getAllByDate(@RequestParam(required = true) String date){
        if(date==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(rentService.findAllForDay(date),HttpStatus.OK);
    }

}
