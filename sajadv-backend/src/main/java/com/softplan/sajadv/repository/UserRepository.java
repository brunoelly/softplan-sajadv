package com.softplan.sajadv.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.softplan.sajadv.user.entity.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCpf(String cpf);
}
