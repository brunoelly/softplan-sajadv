package com.softplan.sajadv.usuario.repository;

import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.softplan.sajadv.usuario.model.Usuario;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario>{

	public Optional<Usuario> findByCpf(String cpf);
}
