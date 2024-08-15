package io.github.rodrigojfagundes.minimarket.tests;

import io.github.rodrigojfagundes.minimarket.dto.CategoryDTO;
import io.github.rodrigojfagundes.minimarket.entities.Category;

public class Factory {
	
	public static Category createCategory() {
		
		Category category = new Category(1L, "name", "description");
		
		return category;
		
	}
	
	public static CategoryDTO createCategoryDTO() {
		Category category = createCategory();
		
		return new CategoryDTO(category);
	}
	
}
