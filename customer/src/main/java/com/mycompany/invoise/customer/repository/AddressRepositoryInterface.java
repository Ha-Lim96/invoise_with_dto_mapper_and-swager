package com.mycompany.invoise.customer.repository;

import com.mycompany.invoise.core.entity.customer.Address;
import org.springframework.data.repository.CrudRepository;


public interface AddressRepositoryInterface extends CrudRepository<Address,Long> {

}