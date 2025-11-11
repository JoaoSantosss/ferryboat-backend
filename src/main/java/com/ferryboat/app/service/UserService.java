package com.ferryboat.app.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferryboat.app.data.dto.UserDTO;
import com.ferryboat.app.data.form.RegisterUserForm;
import com.ferryboat.app.entity.User;
import com.ferryboat.app.entity.enums.Role;
import com.ferryboat.app.exception.UserNotFoundException;
import com.ferryboat.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private UserRepository userRepository;

	public UserDTO createUser(RegisterUserForm form) {
		
		User newUser = buildUser(form);
		
		userRepository.save(newUser);
		
		return toDTO(newUser);
	}
	

	private User buildUser(RegisterUserForm form) {
		User newUser = new User();
		newUser.setEmail(form.getEmail());
		newUser.setActive(true);
		newUser.setCpf(form.getCpf());
		newUser.setName(form.getName());
		newUser.setPassword(passwordEncoder.encode(form.getPassword()));
		newUser.setCreatedAt(Instant.now());
		newUser.setRole(Role.USER);		
		
		return newUser;
	}
	
	
	private UserDTO toDTO(User user) {
	
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		
		return userDTO;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		return user.get();
	}
	
	public UserDTO getUserById(UUID id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new UserNotFoundException(id));
	    return new UserDTO(user);
	}


	public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}