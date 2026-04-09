package com.prajval.CityDealsApp.repositories;

import com.prajval.CityDealsApp.enities.City;
import com.prajval.CityDealsApp.enities.Shop;
import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

//    List<Shop> findByShopStatus(ShopStatus shopStatus);
    List<Shop> findByDeletedFalse();
    List<Shop> findByShopStatusAndDeletedFalse(ShopStatus shopStatus);
    Optional<Shop> findByIdAndDeletedFalse(Long id);

    boolean existsByNameAndCity(String name, City city);
}