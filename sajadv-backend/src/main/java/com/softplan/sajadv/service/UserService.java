package com.softplan.sajadv.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softplan.sajadv.user.entity.User;
import com.softplan.sajadv.user.exception.UserNotFoundException;
import com.softplan.sajadv.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findUserByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}

	public Optional<User> save(User user) {
		return Optional.of(userRepository.save(user));
	}

	public Optional<User> updateUser(User user) {
		if (!userRepository.existsById(user.getId())) {
			throw new UserNotFoundException(user.getId());
		}
		return Optional.of(userRepository.save(user));
	}

	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
	}
}
