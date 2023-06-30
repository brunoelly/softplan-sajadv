package com.softplan.sajadv.Address.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    @NotBlank(message = "Zip Code is mandatory")
    private String zipCode;

    private String country;
    
}
