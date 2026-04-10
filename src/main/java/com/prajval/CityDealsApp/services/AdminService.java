package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import java.util.List;

public interface AdminService {

    ShopDto approveShop(Long shopId);

    ShopDto deactivateShop(Long shopId);

    Void deleteShopById(Long shopId);

    List<UserDto> getAllUsers();

    Void disableUserById(Long userId);

    Void enableUserById(Long userId);

    List<ShopDto> getAllShopsWithFilter(ShopStatus shopStatus);
}
