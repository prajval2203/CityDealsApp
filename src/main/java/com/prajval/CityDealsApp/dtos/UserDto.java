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
    @NotBlank(message = "Name is Mandatory" )
    private String name;
    @NotBlank(message = "Email is Mandatory")
    private String email;
    @NotBlank(message = "Password is Mandatory")
    private String password;
    @NotNull
    private City city;
    @NotNull
    private Set<Role> role;

}
