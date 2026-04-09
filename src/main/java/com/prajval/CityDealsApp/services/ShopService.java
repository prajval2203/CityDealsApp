package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.ShopRequestDto;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<ShopDto> getAllShops(ShopDto shopDto);

    ShopDto createNewShop(ShopRequestDto shopDto);

    ShopDto getShopById(Long shopId);

    ShopDto updateShop(Long shopId, Map<String, Object> updates);
}
