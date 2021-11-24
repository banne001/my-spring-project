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
 Activities class to hold details
 on the where
 @author Blezyl Santos
 @version 11/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private double stars;
}