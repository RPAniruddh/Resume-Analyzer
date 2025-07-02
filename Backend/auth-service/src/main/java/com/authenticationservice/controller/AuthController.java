package com.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationservice.entity.User;
import com.authenticationservice.repository.UserRepository;
import com.authenticationservice.security.JwtUtil;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtil jwtUtils;

	@PostMapping("/signin")
	public String authenticateUser(@RequestBody User user) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return jwtUtils.generateToken(userDetails.getUsername());
	}

	@PostMapping("/signup")
	public String registerUser(@RequestBody User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			return "Error: Email is already taken!";
		}
		// Create new user's account
		User newUser = new User(null, user.getEmail(), encoder.encode(user.getPassword()), user.getName());
		userRepository.save(newUser);
		return "User registered successfully!";
	}
}