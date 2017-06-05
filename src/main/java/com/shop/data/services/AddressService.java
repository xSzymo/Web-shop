package com.shop.data.services;

import com.shop.data.repositories.AddressRepository;
import com.shop.data.tables.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public void save(Address address) {
        repository.save(address);
    }

    public void save(Collection<Address> address) {
        repository.save(address);
    }

    public Iterable<Address> findAll() {
        return repository.findAll();
    }

    public Address findOne(long id) {
        return repository.findOne(id);
    }

}
