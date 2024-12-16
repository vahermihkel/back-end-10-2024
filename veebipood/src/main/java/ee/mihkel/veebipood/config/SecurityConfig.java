package ee.mihkel.veebipood.config;

import ee.mihkel.veebipood.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors().and().headers().xssProtection().disable().and()
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/public-products").permitAll()
                        .requestMatchers("/product").permitAll()
                        .requestMatchers("/find-by-name").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin").permitAll()
                        .requestMatchers("/supplier").permitAll()
                        .requestMatchers("/supplier-escuela").permitAll()
                        .requestMatchers("/parcel-machines/**").permitAll()
                        .requestMatchers("/xml-data").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/products").permitAll()
                        .requestMatchers(HttpMethod.GET,"/categories").permitAll() // GET
                        .requestMatchers(HttpMethod.POST, "/categories").hasAuthority("admin") // POST
                        .requestMatchers("/products/**").hasAuthority("admin")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
                .build();

        // jwtFilter --> JSON Web Token Filter
        // tokenParser
        // tokeniLugeja
    }
}
