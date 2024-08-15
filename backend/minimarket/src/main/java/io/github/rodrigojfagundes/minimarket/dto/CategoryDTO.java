package io.github.rodrigojfagundes.minimarket.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.github.rodrigojfagundes.minimarket.entities.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	
	private List<ProductDTO> products = new ArrayList<>();
	
	public CategoryDTO() {
		
	}

	public CategoryDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	

	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}	
	
	
	
}
