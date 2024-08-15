package io.github.rodrigojfagundes.minimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.minimarket.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
}
