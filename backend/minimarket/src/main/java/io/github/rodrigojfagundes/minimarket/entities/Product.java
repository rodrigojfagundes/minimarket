package io.github.rodrigojfagundes.minimarket.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Positive
	private Long quantity;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	public Product () {
		
	}


	public Product(Long id, String name, String description, Long quantity, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
