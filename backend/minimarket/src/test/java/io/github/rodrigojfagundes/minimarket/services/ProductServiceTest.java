package io.github.rodrigojfagundes.minimarket.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.dto.ProductDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;
import io.github.rodrigojfagundes.minimarket.repositories.CategoryRepository;
import io.github.rodrigojfagundes.minimarket.repositories.ProductRepository;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository repository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private ProductService service;
	
	private Product product0;
	
	private ProductDTO productDTO0;
	
	private Category category0;
	
	private Category category2;
	
	private CategoryDTO categoryDTO0;
	
	private PageImpl<Product> page;
	
	@BeforeEach
	public void setup() {
		category0 = new Category(1L,
				"name1",
				"description1");
		
		categoryDTO0 = new CategoryDTO(1L,
				"name1",
				"description1");
		
		product0 = new Product(null,
				"name1",
				"description",
				1L, category0);		
				
		productDTO0 = new ProductDTO(1L,
				"name",
				"description",
				1L, 
				1L
				);
		
		page = new PageImpl<>(List.of(product0));
		
	}
	
	@DisplayName("JUnit test for Given Product Object When Save product then return Product Object")
	@Test
	void testGivenProductObject_WhenSaveProduct_thenReturnProductObject() {
		
		
		//Given / Arrange
		given(categoryRepository.getOne(productDTO0.getCategoryId())).willReturn(category0);
		given(repository.save(product0)).willReturn(product0);
		//Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product0);
		
		//When / Act
		ProductDTO savedProductDTO = service.inset(productDTO0);
		
		//Then / Assert
		assertNotNull(savedProductDTO);
		assertEquals("name1", savedProductDTO.getName());
		
	}
	

	@Test
	public void testGivenProductPage_WhenFindAllProucts_thenReturnProductsPage(){
		
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Pageable pageable = PageRequest.of(0, 12);
		
		Page<ProductDTO> result = service.findAllPaged(pageable);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, times(1)).findAll(pageable);
		
	}
	
	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() {
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product0));
		
		
		ProductDTO result = service.findById(1L);
		
		
		Assertions.assertNotNull(result);
		
	}
	
	
	@Test
	void testGivenProductObject_WhenUpdateProduct_thenReturnUpdatedProductObject() {
		
		given(repository.getOne(anyLong())).willReturn(product0);
		given(categoryRepository.getOne(anyLong())).willReturn(category0);
		
		product0.setName("nameUpdated");
		productDTO0.setName("nameUpdated");
		
		given(repository.save(product0)).willReturn(product0);
		
		ProductDTO updatedProduct = service.update(1L, productDTO0);
		
		assertNotNull(updatedProduct);
	
		
	}
	
	@Test
	public void deleteSHouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(1L);
		});
		Mockito.verify(repository, times(1)).deleteById(1L);
	}
	
	
}
