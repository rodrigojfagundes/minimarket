package io.github.rodrigojfagundes.minimarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.rodrigojfagundes.minimarket.dto.RoleDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserInsertDTO;
import io.github.rodrigojfagundes.minimarket.dto.UserUpdateDTO;
import io.github.rodrigojfagundes.minimarket.entities.Role;
import io.github.rodrigojfagundes.minimarket.entities.User;
import io.github.rodrigojfagundes.minimarket.repositories.RoleRepository;
import io.github.rodrigojfagundes.minimarket.repositories.UserRepository;
import io.github.rodrigojfagundes.minimarket.services.exceptions.DatabaseException;
import io.github.rodrigojfagundes.minimarket.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<User> users = repository.findAll();
		return users.stream().map(user -> new UserDTO(user))
				.collect(Collectors.toList());		
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User user = obj.orElseThrow(() -> new ResourceNotFoundException(
				"User not found"));
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User user = new User();
		
		copyDtoToEntity(dto, user);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user = repository.save(user);
		
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO update(Long id, UserUpdateDTO userDTO) {
		try {
			User user = repository.getOne(id);
			copyDtoToEntity(userDTO, user);
			user = repository.save(user);
			
			return new UserDTO(user);
			
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("id " + id + " not found");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("id " + id + "not found");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("integrity violation");
		}
	}
	
	public void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setUsername(dto.getUsername());
		
		entity.getRoles().clear();
		for(RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDTO.getId());
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("username not found");
		}
		logger.info("User found: " + username);
		return user;
	}
	
}
