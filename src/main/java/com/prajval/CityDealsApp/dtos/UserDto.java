package com.prajval.CityDealsApp.dtos;

import com.prajval.CityDealsApp.enities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String city;
    private Role role;
    private boolean enabled;
    private String state;
}
