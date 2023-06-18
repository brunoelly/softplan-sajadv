package com.softplan.sajadv.user.repository;

import java.util.Optional;

import com.softplan.sajadv.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByCpf(String cpf);
}
