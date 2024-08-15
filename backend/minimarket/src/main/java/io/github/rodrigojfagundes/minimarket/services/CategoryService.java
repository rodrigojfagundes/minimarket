package io.github.rodrigojfagundes.minimarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.repositories.CategoryRepository;
import io.github.rodrigojfagundes.minimarket.services.exceptions.DatabaseException;
import io.github.rodrigojfagundes.minimarket.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	//FINDALL TIPO LIST
//	@Transactional(readOnly = true)
//	public List<CategoryDTO>findAll(){
//		List<Category> categories = repository.findAll();
//		return categories.stream().map(category -> new CategoryDTO(category))
//				.collect(Collectors.toList());
//	}
	
	//FINDALL TIPO PAGE
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(Pageable pageable) {
		Page<Category> page = repository.findAll(pageable);
		return page.map(category -> new CategoryDTO(category));
	}
	
	
	
	@Transactional
	public CategoryDTO findById(long id) {
		Optional<Category> obj = repository.findById(id);
		Category category = obj.orElseThrow(() -> new ResourceNotFoundException
				("Category não encontrado"));
		return new CategoryDTO(category);
	}
	
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category category = new Category();
		
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		
		category = repository.save(category);
		
		return new CategoryDTO(category);
	}
	
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category category = repository.getOne(id);
			category.setName(dto.getName());
			category.setDescription(dto.getDescription());
			
			category = repository.save(category);
			
			return new CategoryDTO(category);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		} 
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
	
	
	
}
