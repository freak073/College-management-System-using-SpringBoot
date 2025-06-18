package com.app.college.service;

import com.app.college.dto.SignupRequest;
import com.app.college.model.User;
import com.app.college.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        Set<String> roles = new HashSet<>();
        String inputRole = request.getRole() != null ? request.getRole().toUpperCase() : "STUDENT";
        roles.add("ROLE_" + inputRole);
        user.setRoles(roles);

        userRepository.save(user);
    }
}
