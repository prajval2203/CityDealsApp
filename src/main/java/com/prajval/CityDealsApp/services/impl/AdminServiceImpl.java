package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.Shop;
import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.enities.enums.Role;
import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.ShopRepository;
import com.prajval.CityDealsApp.repositories.UserRepository;
import com.prajval.CityDealsApp.services.AdminService;
import com.prajval.CityDealsApp.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    public List<ShopDto> getAllShopsWithFilter(ShopStatus shopStatus) {
        List<Shop> shops;
        if (shopStatus != null) {
            shops = shopRepository.findByShopStatusAndDeletedFalse(shopStatus);
        } else {
            shops = shopRepository.findByDeletedFalse();
        }
        return shops.stream()
                .map(shop -> modelMapper.map(shop, ShopDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto approveShop(Long shopId) {
        Shop shop = shopRepository.findByIdAndDeletedFalse(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found: " + shopId));

        if (shop.getShopStatus() == ShopStatus.APPROVED)
            throw new RuntimeException("Shop is already approved");

        shop.setShopStatus(ShopStatus.APPROVED);
        Shop savedShop = shopRepository.save(shop);
        return modelMapper.map(savedShop, ShopDto.class);
    }

    @Override
    public ShopDto deactivateShop(Long shopId) {
        Shop shop = shopRepository.findByIdAndDeletedFalse(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found: " + shopId));

        if (shop.getShopStatus() == ShopStatus.DEACTIVATED)
            throw new RuntimeException("Shop is already Deactivated");

        shop.setShopStatus(ShopStatus.DEACTIVATED);
        Shop savedShop = shopRepository.save(shop);
        return modelMapper.map(savedShop, ShopDto.class);
    }

    @Override
    public Void deleteShopById(Long shopId) {
        Shop shop = shopRepository
                .findByIdAndDeletedFalse(shopId)
                .orElseThrow(()-> new ResourceNotFoundException("Shop not found with id: " + shopId));
        shop.setDeleted(true);
        shopRepository.save(shop);
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findByRoleNotAndDeletedFalse(Role.ADMIN)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Void disableUserById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));

        if (!user.isEnabled()) {
            throw new RuntimeException("User is already disabled");
        }
        user.setEnabled(false);
        userRepository.save(user);
        refreshTokenService.deleteRefreshToken(user);
        return null;
    }
    @Override
    public Void enableUserById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with id: " + userId));

        if (user.isEnabled()) {
            throw new RuntimeException("User is already enabled");
        }
        user.setEnabled(true);
        userRepository.save(user);
        return null;
    }
}
