package com.prajval.CityDealsApp.repositories;

import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.enities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByRole(Role role);

    void deleteById(Long userId);
}