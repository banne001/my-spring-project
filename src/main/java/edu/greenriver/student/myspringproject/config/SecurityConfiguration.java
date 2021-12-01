package edu.greenriver.student.myspringproject.config;

import edu.greenriver.student.myspringproject.services.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private LoginService service;

    public SecurityConfiguration(LoginService service) {
        this.service = service;
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = encoder();
        //auth.userDetailsService(service).passwordEncoder(encoder);
        auth
            .inMemoryAuthentication()
            .withUser("myuser")
                .password(encoder.encode("pass"))
                .roles("useracct")
            .and()
            .withUser("myadmin")
                .password(encoder.encode("pass"))
                .roles("useracct", "adminacct");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**").and().ignoring().antMatchers("/h2-console/**");
    }
}
