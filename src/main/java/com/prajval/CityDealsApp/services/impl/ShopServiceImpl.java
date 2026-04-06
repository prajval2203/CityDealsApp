package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.enities.Shop;
import com.prajval.CityDealsApp.enities.enums.ShopType;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.ShopRepository;
import com.prajval.CityDealsApp.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ShopDto> getAllShops(ShopDto shopDto) {
        return shopRepository
                .findAll()
                .stream()
                .map((element) -> modelMapper.map(element, ShopDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto createNewShop(ShopDto shopDto) {

        Shop newShop = modelMapper.map(shopDto, Shop.class);
        Shop savedShop = shopRepository.save(newShop);

        return modelMapper.map(savedShop, ShopDto.class);
    }

    @Override
    public ShopDto getShopById(Long shopId) {
        Shop shop = shopRepository
                .findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " +shopId));

        return modelMapper.map(shop, ShopDto.class);
    }

    @Override
    public ShopDto updateShop(Long shopId, Map<String, Object> updates) {
        Shop shop = shopRepository
                .findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + shopId));

        updates.forEach((key, value) -> {

            switch (key){
                case "name" : shop.setName((String) value);
                break;
                case "shopType" : shop.setShopType((ShopType) value);
                    break;
                case "city" : shop.setCity((String) value);
                    break;
            }
        });

        Shop updatedShop = shopRepository.save(shop);
        return modelMapper.map(updatedShop, ShopDto.class);
    }
}
