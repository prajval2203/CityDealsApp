package com.prajval.CityDealsApp.enities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    private Long cityId;
    private String name;
    private String state;
}
