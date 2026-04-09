package com.prajval.CityDealsApp.enities;

import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import com.prajval.CityDealsApp.enities.enums.ShopType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "shops")
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private String state;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus = ShopStatus.PENDING;
    private boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private ShopType shopType;

    @ManyToOne
    private User shopOwner;
}
