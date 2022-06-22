package com.tpe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;

// Repository interface
// 3 tip repository var
// CRUDRepository -> CRUD Operasyonları
// JPARepository -> CRUD + PagingAndSortingRepository
// PagingAndSortingRepository -> Paging ve Sorting

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// @Query("SELECT new com.tpe.dto.StudentDTO(s.id,concat(s.firstName, ' ', s.lastName),s.grade,s.email,s.phoneNumber)...  FROM Student s WHERE s.id=:id")
	
	@Query("SELECT s FROM Student s WHERE s.grade=:pgrade")
	List<Student> findAllEqualsGrade(@Param("pgrade") Integer grade);
	/*
	 * SQL SORGU İÇİN
	@Query(value= "SELECT s FROM Student s WHERE s.grade=:pgrade", nativeQuery=true)
	List<Student> findAllEqualsGradeSQL(@Param("pgrade") Integer grade);
	*/
	
	@Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")
	Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
	
	
	List<Student> findByLastName(String lastName);
	
	List<Student> findByGrade(int grade);
	
	Boolean existsByEmail(String email) throws ConflictException;
	
}
