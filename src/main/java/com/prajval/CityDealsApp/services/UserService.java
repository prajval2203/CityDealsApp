package com.prajval.CityDealsApp.services;


import com.prajval.CityDealsApp.dtos.SignupDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.User;

public interface UserService {
     UserDto signUp(SignupDto signupDto);

     Void deleteUserById(Long userId);

     User getUserById(Long userId);
}
