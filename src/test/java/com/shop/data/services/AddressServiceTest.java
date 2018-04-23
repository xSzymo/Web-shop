package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class AddressServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private AddressService service;

    private LinkedList<Address> addresses;

    @Before
    public void beforeEachTest() {
        addresses = createAddressesCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(addresses);
    }

    @Test
    public void saveOne() {
        Address address = addresses.getFirst();

        service.save(address);

        assertTrue(address.equals(service.findOne(address.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<Address> addresses = this.addresses;

        service.save(addresses);

        addresses.forEach(
                x ->
                        assertTrue(x.equals(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((Address) null);
        } catch (Exception e) {
            fail("Exception should not be throw " + e.getMessage());
        }
    }


    @Test
    public void findOne() {
        service.save(addresses.getFirst());

        Address address = service.findOne(this.addresses.getFirst());

        assertNotNull(address);
    }

    @Test
    public void findOneById() {
        service.save(addresses.getFirst());

        Address address = service.findOne(this.addresses.getFirst().getId());

        assertNotNull(address);
    }

    @Test
    public void findAll() {
        service.save(addresses);

        Iterable<Address> addresses = service.findAll();

        addresses.forEach(
                x ->
                        assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void findNull() {
        try {
            service.findOne((Address) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void delete() {
        Address address = addresses.getFirst();

        service.save(address);
        service.delete(address);

        assertNull(service.findOne(address.getId()));
    }

    @Test
    public void deleteById() {
        Address address = addresses.getFirst();

        service.save(address);
        service.delete(address.getId());

        assertNull(service.findOne(address.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<Address> addresses = this.addresses;

        service.save(addresses);
        service.delete(addresses);

        addresses.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void deleteNull() {
        try {
            service.delete((Address) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    public LinkedList<Address> createAddressesCollection() {
        LinkedList<Address> actualAddress = new LinkedList<>();
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        return actualAddress;
    }
}
