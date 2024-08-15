package io.github.rodrigojfagundes.minimarket.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.rodrigojfagundes.minimarket.dto.UserDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserInsertDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserUpdateDTO;
import io.github.rodrigojfagundes.minimarket.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	private ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> usersDTOs = service.findAll();
		return ResponseEntity.ok().body(usersDTOs);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		UserDTO userDTO = service.findById(id);
		return ResponseEntity.ok(userDTO);
	}
	
	
	@PostMapping 
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO userInsertDTO){
		UserDTO userDTO = service.insert(userInsertDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(
						userDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	
	@PutMapping
	public ResponseEntity<UserDTO> update(@PathVariable Long id, 
			@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
		
		UserDTO userDTO = service.update(id, userUpdateDTO);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}