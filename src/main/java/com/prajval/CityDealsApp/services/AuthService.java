package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.LoginDto;
import com.prajval.CityDealsApp.dtos.LoginResponseDto;
import com.prajval.CityDealsApp.enities.RefreshTokenEntity;
import com.prajval.CityDealsApp.enities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public LoginResponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenService.createRefreshToken(user, refreshToken);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }
//    public String refreshTokenAccess(String refreshToken){
//        RefreshTokenEntity refreshTokenEntity = refreshTokenService.verifyRefreshToken(refreshToken);
//
//        User user = refreshTokenEntity.getUser();
//        return jwtService.generateAccessToken(user);
//    }

    public void logout(String refreshToken){

        RefreshTokenEntity refreshTokenEntity = refreshTokenService.verifyRefreshToken(refreshToken);

        User user = refreshTokenEntity.getUser();
        refreshTokenService.deleteRefreshToken(user);
    }

    public LoginResponseDto refreshToken(String refreshToken) {

        String userEmail = jwtService.getEmailFromToken(refreshToken);
        refreshTokenService.verifyRefreshToken(refreshToken);

        User user = (User) userService.loadUserByUsername(userEmail);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }
}
