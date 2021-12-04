package edu.greenriver.student.myspringproject.config;

import edu.greenriver.student.myspringproject.services.LoginService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 handles all security such as login for user and accessing certain routes
 @author Blezyl Santos
 @version 12/3/2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private LoginService service;
    private BCryptPasswordEncoder encoder;

    /**
     * Constructor to save login and encoder
     *
     * @param service LoginService to help on UserDetails
     * @param encoder to encrypt passwords
     */
    public SecurityConfiguration(LoginService service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/resources/**")
                .and()
                .ignoring().antMatchers("/h2-console/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/bored/user/**")
                        .hasAnyAuthority("user", "admin")
                    .antMatchers("/bored/admin")
                        .hasAuthority("admin")
                    .antMatchers("/**")
                        .permitAll()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .defaultSuccessUrl("/bored")
                    .failureUrl("/login?error=true");
    }
}
