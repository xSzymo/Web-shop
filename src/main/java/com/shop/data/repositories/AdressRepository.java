package com.shop.data.repositories;

import com.shop.data.tables.Address;
import org.springframework.data.repository.CrudRepository;

public interface AdressRepository extends CrudRepository<Address, Long> {

    Address findById(long parseLong);

}
