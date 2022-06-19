package com.tpe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;

// Repository interface
// 3 tip repository var
// CRUDRepository -> CRUD Operasyonları
// JPARepository -> CRUD + PagingAndSortingRepository
// PagingAndSortingRepository -> Paging ve Sorting

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByLastName(String lastName);
	
	Boolean existsByEmail(String email) throws ConflictException;
	
}
