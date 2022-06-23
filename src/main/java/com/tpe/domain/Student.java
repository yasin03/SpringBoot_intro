package com.tpe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "First Name can not be null")
	@NotBlank(message = "First Name can not be white space")
	@Size(min = 1, max = 100, message = "First Name '${validatedValue}' must be between {min} and {max} chars long")
	@Column(nullable = false, length = 100)
	private String firstName;
	
	@Column(nullable = false, length = 100)
	private String lastName;
	
	private Integer grade;
	
	@Email(message = "Provide valid e-mail ")
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	//@Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
	@Column(length = 14)
	private String phoneNumber;
	
	//@JsonFormat(shape = Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
	private LocalDateTime createDate = LocalDateTime.now();
	
	@OneToMany(mappedBy = "student")
	private List<Book> books = new ArrayList<>();
	


}
