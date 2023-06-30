package com.softplan.sajadv.Address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.softplan.sajadv.Address.entity.Address;

@RepositoryRestResource(collectionResourceRel = "address", path = "address")
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
