package com.ferryboat.app.data.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserForm {
	
	private String email;
	private String password;
	private String cpf;
	private String name;

}