package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Address;

public interface AdressRepository extends CrudRepository<Address, Long> {

}
