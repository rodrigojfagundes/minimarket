package io.github.rodrigojfagundes.minimarket.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.rodrigojfagundes.minimarket.dto.UserInsertDTO;
import io.github.rodrigojfagundes.minimarket.entities.User;
import io.github.rodrigojfagundes.minimarket.repositories.UserRepository;
import io.github.rodrigojfagundes.minimarket.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
		
	}
	
	@Override
	public boolean isValid(
			UserInsertDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		User user = repository.findByUsername(dto.getUsername());
		
		if(user != null) {
			list.add(new FieldMessage("username", "Username ja existe"));				
		}
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					e.getMessage()).addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		}
		
		return list.isEmpty();
	}	
}
