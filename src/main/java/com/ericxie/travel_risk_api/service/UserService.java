package com.ericxie.travel_risk_api.service;

import com.ericxie.travel_risk_api.exception.InvalidCredentialsException;
import com.ericxie.travel_risk_api.exception.UsernameAlreadyExistsException;
import com.ericxie.travel_risk_api.model.auth.AuthResponse;
import com.ericxie.travel_risk_api.model.auth.LoginRequest;
import com.ericxie.travel_risk_api.model.auth.RegisterRequest;
import com.ericxie.travel_risk_api.model.auth.User;
import com.ericxie.travel_risk_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already taken");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User new_user = new User();
        new_user.setUsername(request.getUsername());
        new_user.setEmail(request.getEmail());
        new_user.setHashedPassword(hashedPassword);

        userRepository.save(new_user);

        AuthResponse response = new AuthResponse();
        response.setUsername(request.getUsername());
        response.setEmail(request.getEmail());
        response.setUserId(new_user.getId());

        String token = jwtService.generateToken(new_user.getUsername());
        response.setToken(token);

        return response;
    }

    public AuthResponse login(LoginRequest request) {
        User user = this.userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getHashedPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        AuthResponse response = new AuthResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setUserId(user.getId());

        String token = jwtService.generateToken(user.getUsername());
        response.setToken(token);

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getHashedPassword())
                .authorities("USER")
                .build();
    }
}
