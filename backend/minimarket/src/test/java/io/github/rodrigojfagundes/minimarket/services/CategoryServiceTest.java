package io.github.rodrigojfagundes.minimarket.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.repositories.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	
	@Mock
	private CategoryRepository repository;
	
	@InjectMocks
	private CategoryService service;
	
	private Category category0;
	
	private CategoryDTO categoryDTO0;
	
	private PageImpl<Category> page;
	
	@BeforeEach
	public void setup() {
		category0 = new Category(
				null, "name", "description");
		
		categoryDTO0 = new CategoryDTO(
				null, "name", "description");
		
		
		page = new PageImpl<>(List.of(category0));
		
	}
	
	@DisplayName("JUnit test for Given Category Object When Save category then Return Category Object")
	@Test
	void testGivenCategoryObject_WhenSaveCategory_thenReturnCategoryObject() {
		
		//Given / Arrange
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(category0);
		
		//When / Act
		CategoryDTO savedCategoryDTO = service.insert(categoryDTO0);
		
		//Then / Assert
		assertNotNull(savedCategoryDTO);
	
	}
	
	@DisplayName("JUnit test for Given Category Page When Find All Categories Then Return Categories Page")
	@Test
	public void testGivenCategoryPage_WhenFindAllCategories_thenReturnCategoriesPage() {
		
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Pageable pageable = PageRequest.of(0, 12);
		
		Page<CategoryDTO> result = service.findAllPaged(pageable);
		
		Assertions.assertNotNull(result);		
	}
	
	@DisplayName("JUnit test for FindByID Should Return CategoryDTO When Id Exists")
	@Test
	public void findByIdShouldReturnCategoryDTOWhenIdExists() {
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(category0));
		
		CategoryDTO result = service.findById(1L);
		
		Assertions.assertNotNull(result);
		
	}
	
	@DisplayName("JUnit test for Delete Should Do Nothing When Id Exists")
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(1L);
		});
		Mockito.verify(repository, times(1)).deleteById(1L);
	}
	
	@DisplayName("JUnit test for Given CategoryDTO Object When Update Category Then Treturn Updated CategoryDTO Object")
	@Test
	public void testGivenCategoryDTOObject_WhenUpdateCategory_thenReturnUpdatedCategoryDTOObject() {
		
		
		//Given / Arrange
		category0.setId(1L);
		categoryDTO0.setId(1L);
		
		given(repository.getOne(anyLong())).willReturn(category0);
		
		category0.setName("NameUpdated");
		category0.setDescription("DescriptionUpdated");
		
		given(repository.save(category0)).willReturn(category0);
		
		//When / Act
		CategoryDTO updatedCategory = service.update(1L, categoryDTO0);
		
		//Then / Assert
		assertNotNull(updatedCategory);
		
	}
	
	
}
