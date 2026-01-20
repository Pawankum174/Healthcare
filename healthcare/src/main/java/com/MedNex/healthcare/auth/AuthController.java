package com.MedNex.healthcare.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.MedNex.healthcare.dto.LoginRequest;
import com.MedNex.healthcare.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtService jwtService;

	public AuthController(AuthenticationManager authManager, JwtService jwtService) {
		this.authManager = authManager;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())// The
																													// method
																													// getUsername()and
																													// getPassword()
																													// are
																													// undefined
																													// for
																													// the
																													// type
																													// LoginRequest
				);
		String token = jwtService.generateToken(
				(org.springframework.security.core.userdetails.User) authentication.getPrincipal(),
				request.getTenant());
		return new LoginResponse(token);
	}
}
