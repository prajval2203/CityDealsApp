package com.prajval.CityDealsApp.controllers;

import com.prajval.CityDealsApp.dtos.CityDto;
import com.prajval.CityDealsApp.dtos.CityRequestDto;
import com.prajval.CityDealsApp.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/admin/cities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CityDto> createNewCity(@RequestBody CityRequestDto newCity){

        return ResponseEntity.ok(cityService.createNewCity(newCity));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @DeleteMapping("/admin/cities/{city}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCityByName(@PathVariable String city){
        cityService.deleteCityByName(city);
        return ResponseEntity.noContent().build();
    }
}
