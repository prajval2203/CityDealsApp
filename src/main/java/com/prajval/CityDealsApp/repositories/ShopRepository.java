package com.prajval.CityDealsApp.repositories;

import com.prajval.CityDealsApp.enities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndCity(String name, String city);
}