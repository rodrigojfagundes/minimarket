package io.github.rodrigojfagundes.minimarket.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;


@DataJpaTest
public class ProductRepositoryTest {
	
	
	private Product product0;
	private Product product2;
	private Category category0;
	
	@BeforeEach
	public void setup() {
		
		category0 = new Category(1L,
				"name", 
				"description");
		
		product0 = new Product(null, 
				"name", 
				"description", 
				1L ,
				null);

		
		
		product2 = new Product(7L, 
				"name", 
				"description", 
				1L ,
				null);
		
		
	}
	
	@Autowired
	private ProductRepository repository;
	
	
	@DisplayName("JUnit test for Given Product Object when Save Then Return Saved Product")
	@Test
	void testGivenProductObject_whenSave_ThenReturnSavedProduct() {
		
		//Given / Arrange -> nao precisou config pois ja ta no Setup
		//la em cima
		
		//When / Act
		Product savedProduct = repository.save(product0);
		
		//Then / Assert
		assertNotNull(savedProduct);
		assertTrue(savedProduct.getId() > 0);
	}
	
	@DisplayName("JUnit test for Given Product Page when FindAll Then Return Saved Product Page")
	@Test
	void testGivenProductPage_whenFindAll_ThenReturnSavedProductList() {
		
		//Given / Arrange
		Product product1 = new Product(null,
				"Name2",
				"description",
				2L,
				null);
		
		
		repository.save(product0);
		repository.save(product1);
		
		
		List<Product> productPage = repository.findAll();
		
		assertNotNull(productPage);
		
		
	}
	
	@DisplayName("JUnit test for Given Product Object when Find By Id then Return Product Object")
	@Test
	void testGivenProductObject_whenFindById_thenReturnProduct() {
		
		//Given / Arrange
		repository.save(product0);
		
		//When / Act
		Optional<Product> savedProduct = repository.findById(product0.getId());
	
	assertNotNull(savedProduct);
	assertEquals(product0.getId(), savedProduct.get().getId());
	
	}
	
	@DisplayName("JUnit test for Given Product Object when Update Product then Return Updated Product Object")
	@Test
	void testGivenProductObject_whenUpdateProduct_thenReturnUpdate() {
		
		//Given / Arrange
		repository.save(product0);
		
		
		//When / Act
		Product savedProduct = repository.findById(product0.getId()).get();
		
		savedProduct.setName("Name3");
		savedProduct.setDescription("Description3");
		
		Product updatedProduct = repository.save(savedProduct);
		
		//Then / Assert
		assertNotNull(updatedProduct);
		assertEquals("Name3", updatedProduct.getName());
	}
	
	@DisplayName("JUnit test for Given Product Object when Delete then Remove Product")
	@Test
	void testGivenProductObject_whenDelete_thenRemoveProduct() {
		
		//Given / Arrange
		repository.save(product0);
		
		//When / Act
		repository.deleteById(product0.getId());
		
		//When / Act
		Optional<Product> productOptional = repository.findById(product0.getId());
		assertTrue(productOptional.isEmpty());
		
		
		
	}
	
	
	
}
