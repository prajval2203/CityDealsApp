package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.enities.RefreshTokenEntity;
import com.prajval.CityDealsApp.enities.User;

public interface RefreshTokenService {

    RefreshTokenEntity createRefreshToken(User user, String token);
    RefreshTokenEntity verifyRefreshToken(String token);
    void deleteRefreshToken(User user);

}
