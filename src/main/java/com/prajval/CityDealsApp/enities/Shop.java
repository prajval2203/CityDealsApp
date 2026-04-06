package com.prajval.CityDealsApp.enities;

import com.prajval.CityDealsApp.enities.enums.ShopType;
import jakarta.persistence.*;
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
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String state;
    private boolean approved = false;
    private boolean isActive = true;

    private ShopType shopType;

    @ManyToOne
    private User shopOwner;
}
