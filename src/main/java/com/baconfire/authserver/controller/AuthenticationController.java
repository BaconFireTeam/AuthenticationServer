package com.baconfire.authserver.controller;

import com.baconfire.authserver.domain.common.GenericServiceResponse;
import com.baconfire.authserver.domain.common.ServiceStatus;
import com.baconfire.authserver.domain.registration.*;
import com.baconfire.authserver.services.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.baconfire.authserver.domain.AuthenticationRequest;
import com.baconfire.authserver.domain.AuthenticationResponse;
import com.baconfire.authserver.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		final String jwt = jwtTokenUtil.createToken(userDetails);

		AuthenticationResponse response = new AuthenticationResponse(jwt);
		generateResponse(response, true, null, null);

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/employee/generateToken")
	public GenerateTokenResponse registerEmployee(@RequestBody GenerateTokenRequest request) {
		GenerateTokenResponse response = new GenerateTokenResponse();

		if (request == null || request.getEmail() == null) {
			generateResponse(response, false, "99", "Request is invalid!");
			return response;
		}

		response = registrationService.generateEmployeeRegistrationToken(request);
		return response;
	}

	@PostMapping(value = "/employee/validateToken")
	public RegistrationTokenValidationResponse validateEmployeeToken(@RequestBody RegistrationTokenValidationRequest request) {

		RegistrationTokenValidationResponse response = new RegistrationTokenValidationResponse();

		if (request.getEmail() == null || request.getToken() == null) {
			generateResponse(response, false, "99", "Request is invalid!");
			return response;
		}

		response = registrationService.validateUserRegistrationToken(request);

		return response;
	}

	@PostMapping(value = "/employee/register")
	public RegisterResponse registerEmployee(@RequestBody RegisterRequest request) {
		RegisterResponse response = new RegisterResponse();

		if (request.getEmail() == null || request.getUsername() == null || request.getPassword() == null) {
			generateResponse(response, false, "99", "Request is invalid!");
			return response;
		}

		response = registrationService.registerEmployee(request);
		return response;
	}

	@GetMapping(value = "/api/test")
	public ResponseEntity<?> testJwtFilter() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<Object, Object> model = new HashMap<>();
		model.put("username", userDetails.getUsername());
		model.put("roles", userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList())
		);
		return ResponseEntity.ok(model);
	}

	private void generateResponse(GenericServiceResponse response, boolean success, String statusCode, String message) {
		ServiceStatus serviceStatus = ServiceStatus.builder()
				.statusCode(success ? "00" : statusCode)
				.errorMessage(success ? "SUCCESS" : statusCode)
				.success(success)
				.build();

		response.setServiceStatus(serviceStatus);
	}
}