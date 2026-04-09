package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.ShopRequestDto;
import com.prajval.CityDealsApp.enities.City;
import com.prajval.CityDealsApp.enities.Shop;
import com.prajval.CityDealsApp.enities.enums.Role;
import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import com.prajval.CityDealsApp.enities.enums.ShopType;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.CityRepository;
import com.prajval.CityDealsApp.repositories.ShopRepository;
import com.prajval.CityDealsApp.repositories.UserRepository;
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
    private final CityRepository cityRepository;
    private final UserRepository userRepository;


    @Override
    public List<ShopDto> getAllShops(ShopDto shopDto) {
        return shopRepository
                .findAll()
                .stream()
                .map((element) -> modelMapper.map(element, ShopDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto createNewShop(ShopRequestDto shopRequestDto) {

        City city = cityRepository.findById(shopRequestDto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + shopRequestDto.getCityId()));

        if (shopRepository.existsByNameAndCity(shopRequestDto.getName(), city)) {
            throw new RuntimeException("Shop already exists with name: "
                    + shopRequestDto.getName() + " in city: " + city.getCity());
        }

        Shop newShop = Shop.builder()
                .name(shopRequestDto.getName())
                .city(city)
                .shopType(shopRequestDto.getShopType())
                .shopStatus(ShopStatus.PENDING)
                .shopOwner(userRepository.findByRole(Role.SHOP_OWNER))
                .state(shopRequestDto.getState())
                .deleted(false)
                .build();

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
                case "shopType" : shop.setShopType(ShopType.valueOf((String) value));
                    break;
                case "cityId":
                    Long cityId = Long.valueOf(value.toString());
                    City city = cityRepository.findById(cityId)
                            .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + cityId));
                    shop.setCity(city);
                    break;
            }
        });

        Shop updatedShop = shopRepository.save(shop);
        return modelMapper.map(updatedShop, ShopDto.class);
    }
}
