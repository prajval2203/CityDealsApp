package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.dtos.CityDto;
import com.prajval.CityDealsApp.dtos.CityRequestDto;
import com.prajval.CityDealsApp.enities.City;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.CityRepository;
import com.prajval.CityDealsApp.services.CityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    public CityDto createNewCity(CityRequestDto newCity) {

        boolean existingCity = cityRepository.existsByCity(newCity.getCity());

        if (existingCity){
            throw new RuntimeException("City already existed with name: " +newCity.getCity());
        }
        City city = modelMapper.map(newCity, City.class);
        City savedCIty = cityRepository.save(city);
        return modelMapper.map(savedCIty, CityDto.class);
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCityByName(String city) {
        City city1 = cityRepository
                .findByCity(city)
                .orElseThrow(() ->new ResourceNotFoundException("City not found with name: " + city));
        cityRepository.delete(city1);
    }
}
