package com.softplan.sajadv.user.service;

import java.util.List;
import java.util.Optional;

import com.softplan.sajadv.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.sajadv.user.exception.UserNotFoundException;
import com.softplan.sajadv.user.exception.CPFRegistrationException;
import com.softplan.sajadv.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findUserByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}

	public User save(User user) {
		
		if (user.getId() == null) {
			Optional<User> findByCpf = Optional.ofNullable(this.userRepository.findByCpf(user.getCpf())
					.orElseThrow(() -> new UserNotFoundException(user.getId())));

			if (findByCpf.isPresent()) {
				throw new CPFRegistrationException("Cpf já cadastrado na base");
			}
		}
		return userRepository.save(user);
	}

	public User update(User user) {

		if (user.getId() == null) {
			throw new UserNotFoundException(user.getId());
		}

		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
