package com.virtusa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
