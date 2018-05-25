package photopolis.loalityprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import photopolis.loalityprogram.DTO.UserFindDTO;
import photopolis.loalityprogram.service.UserService;

import java.util.List;

/**
 * Created by Anatoliy on 25.05.2018.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/formFindUser", method = RequestMethod.GET)
    private ResponseEntity<List<UserFindDTO>> formFindUser(){
        List<UserFindDTO> users=userService.findAllShort();
        if (users.isEmpty())
            return new ResponseEntity<List<UserFindDTO>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<UserFindDTO>>(users, HttpStatus.OK);
    }
}