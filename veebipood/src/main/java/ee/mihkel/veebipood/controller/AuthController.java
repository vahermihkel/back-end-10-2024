package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.exception.ValidationException;
import ee.mihkel.veebipood.model.EmailPassword;
import ee.mihkel.veebipood.model.Token;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.AuthService;
import ee.mihkel.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<Token> signup(@RequestBody Person person) throws ValidationException {
        authService.validate(person);
        String hashedPassword = encoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.ok().body(authService.getToken(savedPerson));
    }

    @PostMapping("login")
    public Token login(@RequestBody EmailPassword emailPassword) throws ValidationException {
        Optional<Person> personOptional = personRepository.findById(emailPassword.getEmail());
        if (personOptional.isEmpty()) {
            throw new ValidationException("E-mail pole korrektne");
        }
        Person person = personOptional.get();
        if (!encoder.matches(emailPassword.getPassword(), person.getPassword())) {
            throw new ValidationException("Parool pole korrektne");
        };
        return authService.getToken(person);
    }

    @GetMapping("admin")
    public boolean isAdmin() {
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(authority);
        // Filter sees: SecurityContextHolder.getContext().setAuthentication()
    }
}
