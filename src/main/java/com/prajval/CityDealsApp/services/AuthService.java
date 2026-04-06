package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.LoginDto;
import com.prajval.CityDealsApp.dtos.LoginResponseDto;
import com.prajval.CityDealsApp.enities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDto(user.getId(), accessToken);
    }
}
