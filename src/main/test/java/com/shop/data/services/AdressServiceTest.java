package com.shop.data.services;

import com.DataBaseTestConfiguration;
import com.shop.data.tables.Address;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.LinkedList;


public class AdressServiceTest extends DataBaseTestConfiguration {
    @Autowired
    public AddressService repo;

    @BeforeClass
    public static void hcalo() {
        System.out.println("asd");
    }

    @Test
    public void halo() {
        repo.findAll().forEach((x) -> {
            System.out.println(x);
        });
    }

    @Test
    public void asd() {
        Collection<Address> addresses = new LinkedList<Address>();
        addresses.add(new Address("d", "d", "v", "s"));
        addresses.add(new Address("d", "d", "v", "s"));
        addresses.add(new Address("d", "d", "v", "s"));
        repo.save(addresses);
    }


}