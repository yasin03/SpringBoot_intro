package com.tpe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByUserName(String userName) throws ResourceNotFoundException;
	
	
}
