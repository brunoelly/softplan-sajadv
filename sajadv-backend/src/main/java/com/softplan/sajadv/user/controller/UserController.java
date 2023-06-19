package com.softplan.sajadv.user.controller;

import com.softplan.sajadv.assembler.UserModelAssembler;
import com.softplan.sajadv.user.exception.CPFRegistrationException;
import com.softplan.sajadv.user.exception.UserNotFoundException;
import com.softplan.sajadv.user.model.User;
import com.softplan.sajadv.user.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private UserModelAssembler assembler;

	public UserController(UserService userService) {
		this.userService = userService;
		this.assembler = new UserModelAssembler();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> one(@PathVariable Long id) {

		User user = this.userService.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toModel(user);
	}

	@GetMapping("/users/cpf/{cpf}")
	public EntityModel<User> one(@PathVariable String cpf) {

		User user = this.userService.findUserByCpf(cpf)
				.orElseThrow(() -> new CPFRegistrationException(cpf));

		return assembler.toModel(user);
	}

	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> all() {

		List<User> allUsers = userService.getAll();

		List<EntityModel<User>> users = allUsers.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(users,
				linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> create(@Valid @RequestBody User newUser) {

		User savedUser = this.userService.save(newUser);
		EntityModel<User> entityModel = assembler.toModel(savedUser);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel.getContent());
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@RequestBody User newUser, @PathVariable Long id) {

		User updatedUser = this.userService.findUserById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setCpf(newUser.getCpf());
					user.setEmail(newUser.getEmail());
					user.setBirthDate(newUser.getBirthDate());
					user.setAvatar(newUser.getAvatar());
					return this.userService.update(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return this.userService.update(newUser);
				});

		EntityModel<User> entityModel = assembler.toModel(updatedUser);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel.getContent());
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
