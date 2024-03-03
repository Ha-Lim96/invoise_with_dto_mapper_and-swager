package com.mycompany.invoise.customer.repository;

import com.mycompany.invoise.core.entity.customer.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepositoryInterface extends CrudRepository<Customer,Long> {



}