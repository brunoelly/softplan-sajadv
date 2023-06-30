package com.softplan.sajadv.Address.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.softplan.sajadv.Address.entity.Address;
import com.softplan.sajadv.Address.repository.AddressRepository;


@Service
public class AddressService {
    
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }
    
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
