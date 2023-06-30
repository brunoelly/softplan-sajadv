package com.softplan.sajadv.Address.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.sajadv.Address.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    public 
}
