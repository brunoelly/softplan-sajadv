package com.softplan.sajadv.user.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.softplan.sajadv.Address.entity.Address;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "user_sajadv")
@Data
public class User {

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

	@Column(name = "active")
	private Boolean isActive;

	@Lob
	private byte[] avatar;

	@NotBlank(message = "Address is mandatory")
	private Address address;
}
