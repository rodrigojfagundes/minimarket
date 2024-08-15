package io.github.rodrigojfagundes.minimarket.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import io.github.rodrigojfagundes.minimarket.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	@NotBlank(message = "Campo obrigatorio")
	private String name;
	private String username;
	Set<RoleDTO> roles = new HashSet<>();
	
	
	public UserDTO() {
	}


	public UserDTO(Long id, @NotBlank(message = "Campo obrigatorio") String name, String username) {
		this.id = id;
		this.name = name;
		this.username = username;
	}
	
	
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		username = entity.getUsername();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Set<RoleDTO> getRoles() {
		return roles;
	}	
	
}
