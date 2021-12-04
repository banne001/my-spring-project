package edu.greenriver.student.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 Authority implements Granted Authority
 which allows certain roles to be distinguished

 @author Blezyl Santos
 @version 12/3/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public String getAuthority() {
        return role;
    }
}
