package com.example.userservice.services;

import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(String username){
        return userRepository.findUserByUsername(username);
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public  User  addRoleToUser(String username,String roleName){
        User user = userRepository.findUserByUsername(username);

        Role role=roleRepository.findByRolename(roleName);
        user.getRole().add(role);
        return user;
    }

    public  User loadUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

}
