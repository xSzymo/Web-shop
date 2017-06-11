package com.shop.data.services;

import com.shop.data.repositories.AddressRepository;
import com.shop.data.tables.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository repository;

    public void save(Address address) {
        if (address != null)
            repository.save(address);
    }

    public void save(Collection<Address> addresses) {
        addresses.forEach(
                x -> {
                    if (x != null)
                        repository.save(x);
                });
    }

    public Address findOne(long id) {
        try {
            return repository.findOne(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Address findOne(Address address) {
        try {
            return findOne(address.getId());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Iterable<Address> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        try {
            repository.delete(id);
        } catch (NullPointerException e) {

        } catch (EmptyResultDataAccessException e) {

        }
    }

    public void delete(Address address) {
        try {
            delete(address.getId());
        } catch (NullPointerException e) {

        }
    }

    public void delete(Collection<Address> addresses) {
        addresses.forEach(
                x -> {
                    if (x != null)
                        delete(x);
                });
    }
}
