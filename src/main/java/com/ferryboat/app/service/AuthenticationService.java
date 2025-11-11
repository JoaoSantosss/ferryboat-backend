package com.ferryboat.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ferryboat.app.data.AuthenticationResponse;
import com.ferryboat.app.data.dto.UserDTO;
import com.ferryboat.app.data.form.LoginForm;
import com.ferryboat.app.entity.User;
import com.ferryboat.app.repository.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;  
	
	@Autowired
	private JwtService jwtService;

	public AuthenticationResponse authenticateUser(LoginForm form) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						form.getEmail(),
						form.getPassword()));
		
		User user = userRepository.findByEmail(form.getEmail())
				.orElseThrow(() ->  new UsernameNotFoundException("user not found"));
		
		String token = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(token)
				.userDto(new UserDTO(user))
				.build();
	}

}