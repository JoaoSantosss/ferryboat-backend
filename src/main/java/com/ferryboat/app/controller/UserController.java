package com.ferryboat.app.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ferryboat.app.data.AuthenticationResponse;
import com.ferryboat.app.data.dto.UserDTO;
import com.ferryboat.app.data.form.LoginForm;
import com.ferryboat.app.data.form.RegisterUserForm;
import com.ferryboat.app.service.AuthenticationService;
import com.ferryboat.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody RegisterUserForm form) {
		
		UserDTO newUser = userService.createUser(form);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(newUser);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginForm form) {
		
		AuthenticationResponse authenticationResponse = authenticationService.authenticateUser(form);
		return ResponseEntity.ok().body(authenticationResponse);
		
	}
	
	@GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
	    UserDTO userDTO = userService.getUserById(id);
	    return ResponseEntity.ok(userDTO);
	}
	

}
