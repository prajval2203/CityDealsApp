package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.enities.RefreshTokenEntity;
import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.RefreshTokenRepository;
import com.prajval.CityDealsApp.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenEntity createRefreshToken(User user, String token) {

        Optional<RefreshTokenEntity> tokenExists = refreshTokenRepository
                .findByToken(token);

        if (tokenExists.isPresent()){
            refreshTokenRepository.deleteByUser(user);
        }
        RefreshTokenEntity entity = RefreshTokenEntity.builder()
                .token(token)
                .user(user)
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();
        return refreshTokenRepository.save(entity);
    }

    @Override
    public RefreshTokenEntity verifyRefreshToken(String token) {

        RefreshTokenEntity entity = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found: "));
        if (entity.isRevoked()){
            throw new RuntimeException("Refresh token has been revoked");
        }
        if (entity.getExpiresAt().isBefore(LocalDateTime.now())){
                refreshTokenRepository.delete(entity);
                throw new RuntimeException("Refresh token has expired. Pleas login again");
        }
        return entity;
    }
    @Override
    public void deleteRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
