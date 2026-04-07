package com.prajval.CityDealsApp.configs;

import com.prajval.CityDealsApp.enities.User;
import com.prajval.CityDealsApp.enities.enums.Role;
import com.prajval.CityDealsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.name}")
    private String adminName;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.existsByRole(Role.ADMIN)) {
            System.out.println("Admin is already exists");
        }
        else{
            User admin = User.builder()
                    .role(Role.ADMIN)
                    .name(adminName)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .build();
            userRepository.save(admin);
        }
    }
}
