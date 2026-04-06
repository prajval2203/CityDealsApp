package com.prajval.CityDealsApp.dtos;

import com.prajval.CityDealsApp.enities.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {

    @NotBlank(message = "Users Name is Mandatory")
    private String name;
    @Email(message = "Email should be valid in format")
    @NotBlank(message = "Email is Mandatory")
    private String email;
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must be at least 8 characters long, include one uppercase letter, one number, and one special character"
    )
    private String password;
    private String city;

    private Set<Role> role;

}
