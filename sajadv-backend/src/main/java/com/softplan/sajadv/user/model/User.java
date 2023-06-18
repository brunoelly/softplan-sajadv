package com.softplan.sajadv.user.model;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Where(clause = "active = true")
@Data
public class User extends RepresentationModel<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(max = 150, message = "Name cannot be longer than 150 characters")
	private String name;

	@NotBlank(message = "CPF is mandatory")
	@Column(name = "cpf", unique = true)
	private String cpf;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate birthDate;
	
	@Email
	@NotBlank(message = "E-mail is mandatory")
	@Size(max = 400, message = "Email cannot be longer than 400 characters")
	private String email;
	
	private Boolean isActive;

	@Lob
	private byte[] avatar;

}
