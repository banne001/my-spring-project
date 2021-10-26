package edu.greenriver.student.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 Restaurant class to hold details
 on address and stars
 @author Blezyl Santos
 @version 10.20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private double stars;
    private String type;
}
