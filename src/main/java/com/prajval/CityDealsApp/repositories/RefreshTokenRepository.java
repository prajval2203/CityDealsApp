package com.prajval.CityDealsApp.repositories;

import com.prajval.CityDealsApp.enities.RefreshTokenEntity;
import com.prajval.CityDealsApp.enities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

  Optional<RefreshTokenEntity> findByToken(String token);
  void deleteTokenByUser(User user);

  @Transactional
  void deleteRefreshTokenByUser(User user);
}