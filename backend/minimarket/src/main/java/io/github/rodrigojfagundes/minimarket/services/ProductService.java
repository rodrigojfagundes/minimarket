package io.github.rodrigojfagundes.minimarket.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.rodrigojfagundes.minimarket.dto.ProductDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;
import io.github.rodrigojfagundes.minimarket.repositories.CategoryRepository;
import io.github.rodrigojfagundes.minimarket.repositories.ProductRepository;
import io.github.rodrigojfagundes.minimarket.services.exceptions.DatabaseException;
import io.github.rodrigojfagundes.minimarket.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged (Long categoryId, PageRequest pageRequest) {
		Category category = (categoryId == 0) ? null : categoryRepository.getOne(categoryId);
		Page<Product> page = repository.find(category, pageRequest);
		return page.map(product -> new ProductDTO(product));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product product = obj.orElseThrow(() -> new ResourceNotFoundException(
				"Product not found"));
		return new ProductDTO(product);
	}
	
	
	@Transactional
	public ProductDTO inset(ProductDTO dto) {
		Product product = new Product();
		copyDtoToEntity(dto, product);
		
		product = repository.save(product);
		
		return new ProductDTO(product);
	}
	
	
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product product = repository.getOne(id);
			copyDtoToEntity(dto, product);
			product = repository.save(product);
			
			return new ProductDTO(product); 
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch(EmptyResultDataAccessException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		Category category = categoryRepository.getOne(dto.getCategoryId());
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setQuantity(dto.getQuantity());
		entity.setCategory(category);
		
		
	}
	
}
