package photopolis.loalityprogram.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private  ResponseEntity<User> createUser(@RequestParam MultipartFile img, @RequestParam String userJson)  {

        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        User user=new User();
        try {
            user = mapper.readValue(userJson, User.class);
        }
        catch (Exception err)
        {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        if(img.isEmpty()) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        System.out.println(user.toString());
        userService.createUser(
                img, user.getName(), user.getSecondName(), user.getSurname(), user.getPhone(), user.getDateOfBirth(),
                user.getSocialMedia(), user.getCardId(),user.getLastVisit(),
                user.getCardId() != null, user.getDateOfMember(),user.getEmail(),user.getDateOfRegistration());
        return  new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "/find-one/{id}",method = RequestMethod.GET)
    private ResponseEntity<User> findOne(@PathVariable Integer id){
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

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    private ResponseEntity<User> update(@RequestParam String userJson,
                                        @RequestParam(required = false) MultipartFile img){
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);

        User user=new User();
        try {
            user = mapper.readValue(userJson, User.class);
        }
        catch (Exception err)
        {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        if (img ==null) {
            return new ResponseEntity<User>(userService.update(user),HttpStatus.OK);
        }
        return new ResponseEntity<User>(userService.updateWithImg(user,img),HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    private ResponseEntity setUnactive(@PathVariable Integer id){
        if (id.equals(null))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        userService.setUnActive(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
