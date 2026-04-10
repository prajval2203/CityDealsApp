package com.prajval.CityDealsApp.controllers;

import com.prajval.CityDealsApp.dtos.ShopDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.enums.ShopStatus;
import com.prajval.CityDealsApp.services.AdminService;
import com.prajval.CityDealsApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    @PutMapping("/shops/{shopId}/approve")
    public ResponseEntity<ShopDto> approveShop(@PathVariable Long shopId){

        return ResponseEntity.ok(adminService.approveShop(shopId));
    }

    @PutMapping("/shops/{shopId}/deactivate")
    public ResponseEntity<ShopDto> deactivateShop(@PathVariable Long shopId){

        return ResponseEntity.ok(adminService.deactivateShop(shopId));
    }

    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<Void> deleteShopById(@PathVariable Long shopId){
        adminService.deleteShopById(shopId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PutMapping("/users/{userId}/disable")
    public ResponseEntity<Void> disableUser(@PathVariable Long userId){
        adminService.disableUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{userId}/enable")
    public ResponseEntity<Void> enableUser(@PathVariable Long userId){
        adminService.enableUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @GetMapping("/shops")
    public ResponseEntity<List<ShopDto>> getAllShops(
            @RequestParam(required = false) ShopStatus shopStatus) {
        return ResponseEntity.ok(adminService.getAllShopsWithFilter(shopStatus));
    }
}
