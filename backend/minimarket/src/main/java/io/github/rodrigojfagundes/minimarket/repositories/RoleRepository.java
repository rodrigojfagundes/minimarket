package io.github.rodrigojfagundes.minimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rodrigojfagundes.minimarket.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
