package com.ferryboat.app.data.dto;

import java.util.UUID;

import com.ferryboat.app.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private UUID id;
	private String email;
	
	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.email = user.getEmail();
	}
	

}
