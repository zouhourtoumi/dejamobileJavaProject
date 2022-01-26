package com.example.userservice;

import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            System.out.println("hello");

            userService.addRole(new Role(null,"USER"));
            userService.addRole(new Role(null,"ADMIN"));

            userService.addUser(
                    new User(null,"user1", "user@gmail.com","123",new ArrayList<>()));
            userService.addUser(
                    new User(null,"admin", "admin@gmail.com","123",new ArrayList<>()));


            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","ADMIN");
            userRepository.findAll().forEach(System.out::println);

        };
    }



}
