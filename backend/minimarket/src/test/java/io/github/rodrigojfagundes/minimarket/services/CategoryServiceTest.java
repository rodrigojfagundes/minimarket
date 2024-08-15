package io.github.rodrigojfagundes.minimarket.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.repositories.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	
	//
	
	@Mock
	private CategoryRepository repository;
	
	@InjectMocks
	private CategoryService service;
	
	private Category category0;
	
	private CategoryDTO categoryDTO0;
	
	@BeforeEach
	public void setup() {
		category0 = new Category(
				null, "name", "description");
		
		categoryDTO0 = new CategoryDTO(
				null, "name", "description");
	}
	
	@DisplayName("JUnit test for Given Category Object When Save category then return Category Object")
	@Test
	void testGivenPersonObject_WhenSaveCategory_thenReturnCategoryObject() {
		
		//Given / Arrange
		given(repository.save(category0)).willReturn(category0);
		
		//When / Act
		CategoryDTO savedCategoryDTO = service.insert(categoryDTO0);
		
		//Then / Assert
		assertNotNull(savedCategoryDTO);
		assertEquals("name", savedCategoryDTO.getName());
	}
	
	
	@DisplayName("JUnit test for Given Categories List When FindAll Categories then Return Categories List")
	@Test
	void testGivenCategoryList_WhenFindAllCategories_thenReturnCategoriesList() {
		
		//Given / Arrange
		Category category1 = new Category(
				null, "name2", "description2");
		
		CategoryDTO categoryDTO1 = new CategoryDTO(
				null, "name2", "description2");
		
		given(repository.findAll()).willReturn(List.of(category0, category1));
		
		
		//When / Act
		List<CategoryDTO> categoryDTOList = service.findAll();
		
		//Then / Assert		
		assertNotNull(categoryDTOList);
		assertEquals(2, categoryDTOList.size());
		
	}
	
	@DisplayName("JUnit test for Given Category Id When FindById then Return Category Object")
	@Test
	void testGivenCategoryId_WhenFindById_thenReturnCategoryObject() {
		
		//Given / Arrange
		given(repository.findById(anyLong())).willReturn(Optional.of(category0));
		
		//When / Act
		CategoryDTO savedCategoryDTO = service.findById(1L);
		
		//Then / Assert
		assertNotNull(savedCategoryDTO);
		assertEquals("name", savedCategoryDTO.getName());
		
	}
	
	@DisplayName("JUnit test for Given Category Object When Updated Category then Return Updated Category")
	@Test
	void testGivenCategoryObject_WhenUpdateCategory_thenReturnUpdatedCategoryObject() {
		
		//Given / Arrange
		category0.setId(1L);
		categoryDTO0.setId(1L);		
		given(repository.getOne(anyLong())).willReturn(category0);
		
		category0.setName("NameUpdated");
		category0.setDescription("DescriptionUpdated");		
		
		categoryDTO0.setName("NameUpdated");
		categoryDTO0.setDescription("DescriptionUpdated");
		
		given(repository.save(category0)).willReturn(category0);
		
		
		// When / Act
		CategoryDTO updatedCategory = service.update(1L, categoryDTO0);
		
		//Then / Assert
		assertNotNull(updatedCategory);
		assertEquals("NameUpdated", updatedCategory.getName());
		assertEquals("DescriptionUpdated", updatedCategory.getDescription());
	}
	
	@DisplayName("JUnit test for Given Category ID When Delete Category then do Nothing")
	@Test
	void testGivenCategoryID_WhenDeleteCategory_thenDoNothind() {
		
		//Given / Arrange
		
		category0.setId(1L);
		categoryDTO0.setId(1L);
		
//		given(repository.deleteById(anyLong())).willReturn(
//				Optional.of(category0));
		
		
		willDoNothing().given(repository).deleteById(1L);
		
		
		//When / Act
		service.delete(categoryDTO0.getId());
		
		//Then / Assert
		verify(repository, times(1)).deleteById(1L);
	}
	
}
