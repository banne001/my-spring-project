package edu.greenriver.student.myspringproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 BCryptPasswordEncoder class to use dependency injection on
 @author Blezyl Santos
 @version 12/3/2021
 */
@Component
public class EncryptConfig {

    /**
     * Declares BCryptPasswordEncoder
     * @return a new BcryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
