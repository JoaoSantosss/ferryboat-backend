package com.ferryboat.app.data;

import com.ferryboat.app.data.dto.UserDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
	
	private String token;
	private UserDTO userDto;

}