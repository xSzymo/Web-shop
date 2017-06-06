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
		if (address != null)
			repository.save(address);
	}

	public void save(Collection<Address> addresses) {
		if (addresses.size() > 0)
			addresses.forEach(
					x -> {
						if (x != null)
							repository.save(x);
					});
	}

	public Address findOne(long id) {
		return repository.findOne(id);
	}

	public Address findOne(Address address) {
		return repository.findOne(address.getId());
	}

	public Iterable<Address> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		repository.delete(id);
	}

	public void delete(Address address) {
		repository.delete(address.getId());
	}

	public void delete(Collection<Address> addresses) {
		if (addresses.size() > 0)
			repository.delete(addresses);
	}
}
