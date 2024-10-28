package ee.mihkel.veebipood.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public Scanner getScanner() {
//        return new Scanner(System.in);
//    }

//    @Bean
//    public Random getRandom() {
//        return new Random();
//    }
}
