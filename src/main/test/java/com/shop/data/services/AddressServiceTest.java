package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.*;

/*
* done
 */
public class AddressServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private AddressService service;

    private LinkedList<Address> address;

    @Before
    public void beforeEachTest() {
        address = createAddressCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(address);
    }

    @Test
    public void saveOne() {
        Address actualAddress = address.getFirst();

        service.save(actualAddress);

        assertTrue(actualAddress.compareTwoAddress(service.findOne(actualAddress.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<Address> actualAddress = address;

        service.save(actualAddress);

        actualAddress.forEach(
                x ->
                        assertTrue(x.compareTwoAddress(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        Address actualAddress = null;

        try {
            service.save(actualAddress);
        } catch (Exception e) {
            assertNull(e);
        }
    }


    @Test
    public void findOne() {
        service.save(address.getFirst());

        Address address = service.findOne(this.address.getFirst());

        assertNotNull(address);
    }

    @Test
    public void findOneById() {
        service.save(address.getFirst());

        Address actualAddress = service.findOne(this.address.getFirst().getId());

        assertNotNull(actualAddress);
    }

    @Test
    public void findAll() {
        Iterable<Address> actualAddress = service.findAll();

        actualAddress.forEach(
                x ->
                        assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void findNull() {
        Address actualAddress = null;

        try {
            service.findOne(actualAddress);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void delete() {
        Address actualAddress = address.getFirst();

        service.save(actualAddress);
        service.delete(actualAddress);

        assertNull(service.findOne(actualAddress.getId()));
    }

    @Test
    public void deleteById() {
        Address actualAddress = address.getFirst();

        service.save(actualAddress);
        service.delete(actualAddress.getId());

        assertNull(service.findOne(actualAddress.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<Address> actualAddress = address;

        service.save(actualAddress);
        service.delete(actualAddress);

        actualAddress.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void deleteNull() {
        Address actualAddress = null;

        try {
            service.delete(actualAddress);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    public LinkedList<Address> createAddressCollection() {
        LinkedList<Address> actualAddress = new LinkedList<>();
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        actualAddress.add(new Address("street", "postal code", "city", "country"));
        return actualAddress;
    }
}
