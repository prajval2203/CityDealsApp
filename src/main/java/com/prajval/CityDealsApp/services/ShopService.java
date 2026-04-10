package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.ShopRequestDto;
import java.util.Map;

public interface ShopService {
    ShopDto createNewShop(ShopRequestDto shopDto);

    ShopDto getShopById(Long shopId);

    ShopDto updateShop(Long shopId, Map<String, Object> updates);
}
