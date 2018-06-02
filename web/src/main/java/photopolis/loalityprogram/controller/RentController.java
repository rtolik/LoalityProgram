package photopolis.loalityprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import photopolis.loalityprogram.DTO.DailyRentDTO;
import photopolis.loalityprogram.DTO.RentUserDTO;
import photopolis.loalityprogram.model.Rent;
import photopolis.loalityprogram.service.RentService;

import java.util.List;

import static photopolis.loalityprogram.service.utils.Utility.doubleTimeToStringParser;
import static photopolis.loalityprogram.service.utils.Utility.timeToDoubleParser;

/**
 * Created by Anatoliy on 25.05.2018.
 */
@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;


    @RequestMapping(value = "/find-all-by-date",method = RequestMethod.GET)
    private ResponseEntity<List<DailyRentDTO>> getAllByDate(@RequestParam(required = true) String date){
        if(date==null)
            return new ResponseEntity<List<DailyRentDTO>>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<List<DailyRentDTO>>(rentService.findAllForDay(date),HttpStatus.OK);
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    private ResponseEntity<RentUserDTO> findOne(@PathVariable Integer id){
        if(id.equals(null))
            return new ResponseEntity<RentUserDTO>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<RentUserDTO>(rentService.findOneDTO(id),HttpStatus.OK);
    }

    @RequestMapping(value = "find-all-by-user-id/{id}",method = RequestMethod.GET)
    private ResponseEntity<List<Rent>> findAllByUserId(@PathVariable Integer id){
        if(id.equals(null))
            return new ResponseEntity<List<Rent>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Rent>>(rentService.findAllByUserId(id),HttpStatus.OK);
    }

    @RequestMapping(value = "save/{userId}",method = RequestMethod.POST)
    private ResponseEntity createRent(@PathVariable Integer userId, @RequestParam String date,
                                      @RequestParam String timeOfStart,
                                      @RequestParam Double duration,
                                      @RequestParam String comment){
        if (userId.equals(null)||date.equals(null) || timeOfStart.equals(null) || duration.equals(null)
                || comment.equals(null)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        rentService.createNewRent(userId, date, timeOfStart, duration, comment);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}/{date}/{timeOfStart}/{duration}",method = RequestMethod.POST)
    private ResponseEntity<RentUserDTO> update(@PathVariable  Integer id, @PathVariable String date,
                                               @PathVariable  String timeOfStart, @PathVariable  Double duration,
                                               @RequestParam String comment){
        if (id.equals(null)||date.equals(null)||timeOfStart.equals(null)||duration.equals(null)||comment.equals(null))
            return new ResponseEntity<RentUserDTO>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<RentUserDTO>(rentService.update(id, date, timeOfStart, duration,comment),HttpStatus.OK);
    }

    @RequestMapping(value = "/submit/{id}/{price}/{bonusPrice}",method = RequestMethod.POST)
    private ResponseEntity<RentUserDTO> submitRent(@PathVariable Integer id, @PathVariable Double price,
                                                   @PathVariable Double bonusPrice){
        if(id.equals(null)|| price.equals(null)||bonusPrice.equals(null))
            return new ResponseEntity<RentUserDTO>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<RentUserDTO>(rentService.submitRent(id,price,bonusPrice),HttpStatus.OK);
    }
}
