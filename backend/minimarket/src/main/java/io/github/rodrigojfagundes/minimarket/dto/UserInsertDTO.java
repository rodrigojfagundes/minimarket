package io.github.rodrigojfagundes.minimarket.dto;

import io.github.rodrigojfagundes.minimarket.services.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

	private static final long serialVersionUID = 1L;
	
	
	private String password;
	
	
	public UserInsertDTO() {
		super();
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
