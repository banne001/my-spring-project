package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.UserRepository;
import edu.greenriver.student.myspringproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 Main function to run Spring application
 @author Blezyl Santos
 @version 12/3/2021
 */
@Service
public class LoginService implements UserDetailsService {
    private UserRepository repo;
    private BCryptPasswordEncoder encoder;

    /**
     * Constructor to save repo and encrypt password
     * @param repo save data to the database
     * @param encoder to encrypt password
     */
    public LoginService(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("username is not recognized");
    }

    /**
     * Utilizes repo to save to the database
     * @param user Saves user to the database
     * @return user that is saved
     */
    public User save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    /**
     * Gets all the users in the database
     * @return all users in the database
     */
    public List<User> allUsers(){
        return repo.findAll();
    }
}
