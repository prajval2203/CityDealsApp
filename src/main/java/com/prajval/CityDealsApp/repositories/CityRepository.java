package com.prajval.CityDealsApp.repositories;

import com.prajval.CityDealsApp.enities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);
    boolean existsByCity(String city);

}
