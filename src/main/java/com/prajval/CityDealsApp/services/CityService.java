package com.prajval.CityDealsApp.services;

import com.prajval.CityDealsApp.dtos.CityDto;
import com.prajval.CityDealsApp.dtos.CityRequestDto;

import java.util.List;

public interface CityService {
    CityDto createNewCity(CityRequestDto newCity);

    List<CityDto> getAllCities();

    void deleteCityByName(String city);
}
