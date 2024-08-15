package io.github.rodrigojfagundes.minimarket.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	//OBS TA ESCRITO FINDALL MAS ERA FIND
	//@Query("SELECT obj FROM Product obj WHERE :category IS NULL OR obj.category = :category")
	//Page<Product> findAll(Category category, Pageable pageable);
	
	
	
	
}
