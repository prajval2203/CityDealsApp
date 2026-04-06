package com.prajval.CityDealsApp.services;


import com.prajval.CityDealsApp.dtos.SignupDto;
import com.prajval.CityDealsApp.dtos.UserDto;

public interface UserService {
     UserDto signUp(SignupDto signupDto);
}
