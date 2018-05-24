package photopolis.loalityprogram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anatoliy on 24.05.2018.
 */
@RequestMapping("/Hello")
@RestController
public class HelloWorldController {

    @RequestMapping(value = "/world",method = RequestMethod.GET)
    private ResponseEntity<String> helloworld()
    {
        return new ResponseEntity<String>("Helloworld", HttpStatus.OK);
    }
}
