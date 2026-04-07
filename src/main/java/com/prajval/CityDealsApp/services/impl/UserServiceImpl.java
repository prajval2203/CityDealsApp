package com.prajval.CityDealsApp.services.impl;

import com.prajval.CityDealsApp.dtos.SignupDto;
import com.prajval.CityDealsApp.dtos.UserDto;
import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.exceptions.ResourceNotFoundException;
import com.prajval.CityDealsApp.repositories.UserRepository;
import com.prajval.CityDealsApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }


    @Override
    public UserDto signUp(SignupDto signupDto) {

        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("User with this " + signupDto.getEmail() + "already exists");
        }
        User newUser  = modelMapper.map(signupDto, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        User savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow();
        userRepository.deleteById(userId);
    }
}
