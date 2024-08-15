package io.github.rodrigojfagundes.minimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.minimarket.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
