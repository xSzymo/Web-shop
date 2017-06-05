package com.shop.data.repositories;

import com.shop.data.tables.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Address findById(long parseLong);

}
