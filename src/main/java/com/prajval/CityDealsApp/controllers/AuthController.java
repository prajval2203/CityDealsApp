package com.prajval.CityDealsApp.controllers;

import com.prajval.CityDealsApp.dtos.LoginDto;
import com.prajval.CityDealsApp.dtos.LoginResponseDto;
import com.prajval.CityDealsApp.dtos.SignupDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.services.AuthService;
import com.prajval.CityDealsApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<UserDto> signUp(@Valid @RequestBody SignupDto signupDto){

        return ResponseEntity.ok(userService.signUp(signupDto));
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto,
                                                   HttpServletRequest request, HttpServletResponse response){

        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("accessToken", loginResponseDto.getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }
}
