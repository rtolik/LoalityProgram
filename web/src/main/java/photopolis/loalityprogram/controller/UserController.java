package photopolis.loalityprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photopolis.loalityprogram.DTO.PageFinderDTO;
import photopolis.loalityprogram.DTO.UserFIndClientDTO;
import photopolis.loalityprogram.DTO.UserFindDTO;
import photopolis.loalityprogram.model.User;
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

    @RequestMapping(value = "/form-find-user", method = RequestMethod.GET)
    private ResponseEntity<List<UserFindDTO>> formFindUser(){
        List<UserFindDTO> users=userService.findAllShort();
        if (users.isEmpty())
            return new ResponseEntity<List<UserFindDTO>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<UserFindDTO>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-client",method = RequestMethod.GET)
    private ResponseEntity<List<UserFIndClientDTO>> findClient(){
        List<UserFIndClientDTO> users = userService.findAllClieants();
        if (users.isEmpty())
            return new ResponseEntity<List<UserFIndClientDTO>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<UserFIndClientDTO>>(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    private  ResponseEntity createUser(@RequestParam MultipartFile img, @RequestParam String name,
                                       @RequestParam String secondName, @RequestParam String surname,
                                       @RequestParam String phone, @RequestParam String dateOfBirth,
                                       @RequestParam String socialMedia, @RequestParam Integer cardId,
                                       @RequestParam String lastVisit, @RequestParam Integer numberOfVisits,
                                       @RequestParam String dateOfMember) {

        if(img.isEmpty() || name.equals(null) || surname.equals(null) ||phone.equals(null)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        userService.createUser(
                img, name, secondName, surname, phone, dateOfBirth, socialMedia, cardId, lastVisit,
                numberOfVisits, cardId != null,dateOfMember);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = ".find-one",method = RequestMethod.GET)
    private ResponseEntity<User> findOne(@RequestParam Integer id){
        if(id==null)
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<User>(userService.findOne(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/find-all-pageable-active/{pageNumber}/{elOnPage}/{userName}/{userMode}/{criterion}",
                    method = RequestMethod.GET)
    private ResponseEntity<PageFinderDTO> pageFinderDTOResponseEntity(@PathVariable Integer pageNumber,
                                                                      @PathVariable Integer elOnPage,
                                                                      @PathVariable String userName,
                                                                      @PathVariable String userMode,
                                                                      @PathVariable String criterion){
        if(pageNumber.equals(null) ||elOnPage.equals(null) || userName.equals(null) || userMode.equals(null)
                || criterion.equals(null))
            return new ResponseEntity<PageFinderDTO>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<PageFinderDTO>(
                userService.pageParserFilter(userName,pageNumber,elOnPage,userMode,criterion),
                HttpStatus.OK
        );
    }
}
