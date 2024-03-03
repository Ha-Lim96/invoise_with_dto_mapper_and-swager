package com.mycompany.invoise.customer.api;

import com.mycompany.invoise.core.entity.customer.Address;
import com.mycompany.invoise.customer.dto.AddressDto;
import com.mycompany.invoise.customer.mapper.AddressMapper;
import com.mycompany.invoise.customer.repository.AddressRepositoryInterface;
import com.mycompany.invoise.customer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/address")
public class AddressResource {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public AddressDto get(@PathVariable("id") Long id){
        return addressService.getAddress(id);
    }
}
