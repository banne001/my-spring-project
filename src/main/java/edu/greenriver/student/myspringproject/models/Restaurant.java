package edu.greenriver.student.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Restaurant {

    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private double stars;
}
