package ee.mihkel.veebipood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(@Lazy AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            System.out.println(token);

            String securityKey = "aYulPsol0frkZYczXIlYhzKYW2O9JVCEMj9CCt7hgeVwWLar7p9KnO96gK9qv2G4xXXbNaHzznywudEv+H57WA==";
            SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));

           Claims claims = Jwts.parser()
                   .verifyWith(signingKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();

//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey)))
//                    .build()
//                    .parseClaimsJws(requestToken)
//                    .getBody();
            System.out.println(claims.get("email"));
            System.out.println(claims.get("firstName"));
            System.out.println(claims.get("lastName"));

            String email = claims.get("email").toString();
            String credentials = claims.get("firstName") + " " + claims.get("lastName");
            boolean admin = claims.get("admin").equals("true");

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (admin) {
                GrantedAuthority authority = new SimpleGrantedAuthority("admin");
                authorities.add(authority);
            }

            // tokeni seest e-mail       tokeni seest crendentials
            Authentication auth = new UsernamePasswordAuthenticationToken(email, credentials, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth); // see rida teeb autentituks
        }

        super.doFilterInternal(request, response, chain);
    }
}
