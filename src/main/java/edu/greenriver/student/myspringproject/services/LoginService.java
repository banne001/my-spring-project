package edu.greenriver.student.myspringproject.services;

import edu.greenriver.student.myspringproject.dbs.UserRepository;
import edu.greenriver.student.myspringproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    private UserRepository repo;
    private BCryptPasswordEncoder encoder;

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

    public User save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> allUsers(){
        return repo.findAll();
    }
}
