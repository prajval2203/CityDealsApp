package com.prajval.CityDealsApp.dtos;

import com.prajval.CityDealsApp.enities.City;
import com.prajval.CityDealsApp.enities.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String cityName;
    private Role role;

}
