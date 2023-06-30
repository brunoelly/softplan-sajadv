package com.softplan.sajadv.user.controller;

import com.softplan.sajadv.user.entity.User;
import com.softplan.sajadv.user.exception.CPFRegistrationException;
import com.softplan.sajadv.user.exception.UserNotCreatedException;
import com.softplan.sajadv.user.exception.UserNotFoundException;
import com.softplan.sajadv.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> allUsers = userService.getAll();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping("/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	@GetMapping("/cpf/{cpf}")
	public User findUserByCPF(@PathVariable String cpf) {
		return userService.findUserByCpf(cpf)
				.orElseThrow(() -> new CPFRegistrationException(cpf));
	}

	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User newUser) {
		User user = userService.save(newUser)
				.orElseThrow(() -> new UserNotCreatedException(newUser.getId()));
		return ResponseEntity.created(linkTo(methodOn(UserController.class)
				.findUserById(user.getId())).toUri())
				.body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User userToUpdate, @PathVariable Long id) {
		userToUpdate.setId(id);
		return userService.updateUser(userToUpdate)
				.map(updatedUser -> ResponseEntity.created(linkTo(methodOn(UserController.class)
						.findUserById(updatedUser.getId())).toUri()).body(updatedUser))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(CPFRegistrationException.class)
	public ResponseEntity<String> handleCPFRegistration(CPFRegistrationException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}
}
