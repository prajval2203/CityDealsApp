package com.prajval.CityDealsApp.enities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String state;
}
