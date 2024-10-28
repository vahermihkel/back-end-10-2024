package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.PersonAddressDTO;
import ee.mihkel.veebipood.entity.Address;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    // localhost:8080/address?street=ta
    @GetMapping("/address")
    public List<PersonAddressDTO> getProducts(@RequestParam String street) {
//        System.out.println("street: " + street);
        log.error("error: {}", street);
        List<Person> persons = personRepository.findByAddress_StreetContainsIgnoreCase(street);
        return personService.getPersonAddressDTO(persons);
    }
}
