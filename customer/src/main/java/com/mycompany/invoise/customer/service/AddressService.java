package com.mycompany.invoise.customer.service;

import com.mycompany.invoise.core.entity.customer.Address;
import com.mycompany.invoise.customer.dto.AddressDto;
import com.mycompany.invoise.customer.mapper.AddressMapper;
import com.mycompany.invoise.customer.repository.AddressRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressService {
    private final AddressRepositoryInterface addressRepositoryInterface;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepositoryInterface addressRepositoryInterface, AddressMapper addressMapper) {
        this.addressRepositoryInterface = addressRepositoryInterface;
        this.addressMapper = addressMapper;
    }

    public AddressDto getAddress(@PathVariable("id") Long id){
        return addressRepositoryInterface.findById(id)
                .map(address -> addressMapper.map(address))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
