package io.github.rodrigojfagundes.minimarket.dto;

import java.io.Serializable;

import io.github.rodrigojfagundes.minimarket.entities.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	private Long quantity;
	private Long categoryId;
	
	public ProductDTO() {
		
	}

	public ProductDTO(Long id, String name, String description, Long quantity, Long categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.categoryId = categoryId;
	}
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		quantity = entity.getQuantity();
		
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
		
	
}
