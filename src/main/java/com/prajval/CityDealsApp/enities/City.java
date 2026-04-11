package com.prajval.CityDealsApp.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String city;
    private String state;
}
