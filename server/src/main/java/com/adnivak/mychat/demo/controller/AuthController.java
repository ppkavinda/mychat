package com.adnivak.mychat.demo.controller;

import com.adnivak.mychat.demo.payload.dto.AuthResponseDTO;
import com.adnivak.mychat.demo.security.JwtTokenProvider;
import com.adnivak.mychat.demo.exception.AppException;
import com.adnivak.mychat.demo.model.Role;
import com.adnivak.mychat.demo.model.RoleName;
import com.adnivak.mychat.demo.model.User;
import com.adnivak.mychat.demo.payload.ApiResponse;
import com.adnivak.mychat.demo.payload.JwtAuthenticationResponse;
import com.adnivak.mychat.demo.payload.LoginRequest;
import com.adnivak.mychat.demo.payload.SignUpRequest;
import com.adnivak.mychat.demo.repository.RoleRepository;
import com.adnivak.mychat.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(ApiResponse.error(HttpStatus.UNAUTHORIZED.toString(), "Unable to Register",
                    "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(ApiResponse.error(HttpStatus.UNAUTHORIZED.toString(), "Unable to Register",
                    "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        AuthResponseDTO authResponse = AuthResponseDTO.builder().message("User registered successfully!").build();
        return ResponseEntity.created(location).body(ApiResponse.ok(authResponse, "auth"));
    }
}