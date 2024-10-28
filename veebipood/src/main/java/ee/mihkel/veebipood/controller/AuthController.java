package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    PersonService personService;

    @PostMapping("signup")
    public String signup(@RequestBody Person person) {
        personService.savePerson(person);
        return "Tagastan sisselogimise tokeni";
    }
}
