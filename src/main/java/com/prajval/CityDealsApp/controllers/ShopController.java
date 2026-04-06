package com.prajval.CityDealsApp.controllers;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.services.ShopService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
@Validated
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shops")
    public List<ShopDto> getAllShops(ShopDto shopDto){
        return shopService.getAllShops(shopDto);
    }

    @PostMapping
    public ResponseEntity<ShopDto> createShop(@Valid @RequestBody ShopDto shopDto){
        return ResponseEntity.ok(shopService.createNewShop(shopDto));
    }

    @GetMapping("/{shopId}")
    public ShopDto getShopById(@PathVariable @Min(1) Long shopId){
        return shopService.getShopById(shopId);
    }

    @PatchMapping("/{shopId}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable @Min(1) Long shopId, @RequestBody Map<String, Object> updates){

        ShopDto updatedShop = shopService.updateShop(shopId, updates);
        return ResponseEntity.ok(updatedShop);
    }
}
