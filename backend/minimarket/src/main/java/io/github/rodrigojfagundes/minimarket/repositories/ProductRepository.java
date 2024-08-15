package io.github.rodrigojfagundes.minimarket.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.rodrigojfagundes.minimarket.entities.Category;
import io.github.rodrigojfagundes.minimarket.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

	
}
