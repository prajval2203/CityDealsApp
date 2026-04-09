package com.prajval.CityDealsApp.dtos;

import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import com.prajval.CityDealsApp.enities.enums.ShopType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequestDto {

    @NotNull(message = "City is mandatory")
    private Long cityId;
    private String name;
    private CityDto city;
    private String state;
    private ShopType shopType;
    private boolean deleted;
    private ShopStatus shopStatus;
    private String shopOwner;
}
