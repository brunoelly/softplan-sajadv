package com.softplan.sajadv.user.repository;

import java.util.Optional;

import com.softplan.sajadv.user.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCpf(String cpf);
}
