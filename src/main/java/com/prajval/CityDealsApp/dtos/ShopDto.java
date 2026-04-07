package com.prajval.CityDealsApp.dtos;

import com.prajval.CityDealsApp.enities.City;
import com.prajval.CityDealsApp.enities.enums.ShopType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private Long id;
    @NotBlank(message = "Shop Name is Mandatory")
    @Size(min = 3, max = 100, message = "Shop Name must be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "City Name is Mandatory")
    private String cityName;
    @NotBlank(message = "State Name is Mandatory")
    private String state;
    @NotNull
    private ShopType shopType;
    private boolean approved = false;
    private boolean isActive = false;
}
