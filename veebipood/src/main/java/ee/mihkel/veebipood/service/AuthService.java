package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.exception.ValidationException;
import ee.mihkel.veebipood.model.Token;
import ee.mihkel.veebipood.util.ValidationUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {


    public Token getToken(Person person) {
        Date expirationDate = new Date((new Date()).getTime() + 2 * 3600*1000);
        Map<String, String> claims = new HashMap<>();
        claims.put("email", person.getEmail());
        claims.put("firstName", person.getFirstName());
        claims.put("lastName", person.getLastName());
        claims.put("admin", String.valueOf(person.isAdmin()));

        String securityKey = "aYulPsol0frkZYczXIlYhzKYW2O9JVCEMj9CCt7hgeVwWLar7p9KnO96gK9qv2G4xXXbNaHzznywudEv+H57WA==";
        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));

        String jwtToken = Jwts.builder()
                .expiration(expirationDate)
                .claims(claims)
                .signWith(signingKey)
                .compact();
//        String jwtToken = "123";

//        String jwtToken = Jwts.builder()
//                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)), SignatureAlgorithm.HS512)
//                .setIssuer("Mihkel's webshop")
//                .setExpiration(expiration)
//                .setSubject(person.getId().toString())
//                .setAudience(String.valueOf(person.isAdmin()))
//                .compact();

        Token token = new Token();
        token.setToken(jwtToken);
        token.setExpiration(new Date());
        return token;
    }

    public void validate(Person person) throws ValidationException {
        if (person.getEmail() == null || person.getEmail().isEmpty()) {
            //return ResponseEntity.badRequest().body("Email cannot be empty");
            throw new ValidationException("Email cannot be empty");
        }
        if (!ValidationUtil.validateEmail(person.getEmail())) {
            throw new ValidationException("Email is not correct");
        }
        if (person.getPassword() == null || person.getPassword().isEmpty()) {
            throw new ValidationException("Password cannot be empty");
        }
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new ValidationException("First name cannot be empty");
        }
        if (person.getLastName() == null || person.getLastName().isEmpty()) {
            throw new ValidationException("Last name cannot be empty");
        }
    }


}
