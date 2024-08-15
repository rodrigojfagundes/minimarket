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

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;

@DataJpaTest
public class CategoryRepositoryTest {
	
	private Category category0;
	
	@BeforeEach
	public void setup() {
		category0 = new Category(null, 
				"Nome", 
				"description");
	}
	
	@Autowired
	private CategoryRepository repository;
	
	
	@DisplayName("JUnit test for Given Category Object when Save Then Return Saved Category")
	@Test
	void testGivenCategoryObject_whenSave_ThenReturnSavedCategory() {
		
		//Given / Arrange -> nao precisou config pois ja ta no SETUP
		//la em cima
		
		//When / act
		Category savedCategory = repository.save(category0);
		
		//Then / Assert
		assertNotNull(savedCategory);
		assertTrue(savedCategory.getId() > 0);
	}
	
	@DisplayName("JUnit test for Given Category List when findAllThen Return Saved Category List")
	@Test
	void testGivenCategoryList_whenFindAll_ThenReturnSavedCategoryList() {
		
		//Given / Arrange
		Category category1 = new Category
				(null, "Name2", "Description2");
		
		repository.save(category0);
		repository.save(category1);
		
		//When / Act
		List<Category> categoryList = repository.findAll();
		
		//Then / Assert
		assertNotNull(categoryList);
		assertEquals(2, categoryList.size());
	}
	
	@DisplayName("JUnit test for Given Category Object when Find By Id then Return Category Object")
	@Test
	void testGivenCategoryObject_whenFindById_thenReturnCategoryObject() {
		
		//Given / Arrange
		repository.save(category0);
		
		//When / Act
		Category savedCategory = repository.findById(category0.getId()).get();
		
		//Then / Assert
		assertNotNull(savedCategory);
		assertEquals(category0.getId(), savedCategory.getId());
		
		
	}
	
	@DisplayName("JUnit test for Given Category Object when Update Category then Return Updated Category Object")
	@Test
	void testGivenCategoryObject_whenUpdateCategory_thenReturnUpdatedCategoryObject() {
		 
		//Given / Arrange
		repository.save(category0);
		
		//When / Act
		Category savedCategory = repository.findById(category0.getId()).get();
		
		savedCategory.setName("Nome3");
		savedCategory.setDescription("NomeBom");
		
		Category updatedCategory = repository.save(savedCategory);
		
		//Then / Assert
		assertNotNull(updatedCategory);
		assertEquals("Nome3", updatedCategory.getName());
		assertEquals("NomeBom", updatedCategory.getDescription());
	 }
	
	@DisplayName("JUnit test for Given Category Object when Delete then Remove Category")
	@Test
	void testGivenCategoryObject_whenDelete_thenRemoveCategory() {
		
		//Given / Arrange
		repository.save(category0);
		
		//When / Act
		repository.deleteById(category0.getId());
		
		//Then / Assert
		Optional<Category> categoryOptional = repository.findById(category0.getId());
		
		assertTrue(categoryOptional.isEmpty());
		
	}
	
}
