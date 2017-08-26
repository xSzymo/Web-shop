package com.shop.data.services;


import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.Cookies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;

public class CookiesServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private CookiesService service;

    private LinkedList<Cookies> cookies;

    @Before
    public void BeforeEachTest() {
        cookies = createCookiesCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(cookies);
    }

    @Test
    public void save() {
        Cookies cookie = cookies.getFirst();

        service.save(cookie);

        assertTrue(cookie.equals(service.findOne(cookie.getId())));
    }

    @Test
    public void saveOne() {
        Cookies cookie = cookies.getFirst();

        service.save(cookie);

        assertTrue(cookie.equals(service.findOne(cookie.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<Cookies> cookies = this.cookies;

        service.save(cookies);

        cookies.forEach(
                x -> assertTrue(x.equals(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((Cookies) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void saveOneWithExistName() {
        cookies.add(new Cookies("category " + 6, "category " + 6));
        service.save(cookies);

        for (Cookies x : service.findAll()) {
            int numberOfSameObject = 0;
            for (Cookies x1 : service.findAll()) {
                if (x.getName().equals(x1.getName()))
                    numberOfSameObject++;
                if (numberOfSameObject > 1)
                    fail("can't save two cookies with same name");
            }
        }
    }

    @Test
    public void findOne() {
        service.save(cookies.getFirst());

        Cookies cookies = service.findOne(this.cookies.getFirst());

        assertNotNull(cookies);
    }

    @Test
    public void findOneWithNull() {
        try {
            service.findOne((Cookies) null);
            service.findOneByName((Cookies) null);
            service.findOne(null);
            service.findOneByName((String) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void findOneById() {
        service.save(cookies.getFirst());

        Cookies cookie = service.findOne(cookies.getFirst().getId());

        assertNotNull(cookie);
    }

    @Test
    public void findOneByNameWithString() {
        service.save(cookies.getFirst());

        Cookies cookie = service.findOneByName(cookies.getFirst().getName());

        assertNotNull(cookie);
    }

    @Test
    public void findOneByValueWithString() {
        service.save(cookies.getFirst());

        Cookies cookie = service.findOneByValue(cookies.getFirst().getValue());

        assertNotNull(cookie);
    }

    @Test
    public void findOneByValueWithObject() {
        service.save(cookies.getFirst());

        Cookies cookie = service.findOneByValue(cookies.getFirst());

        assertNotNull(cookie);
    }

    @Test
    public void findOneByNameWithObject() {
        service.save(cookies.getFirst());

        Cookies cookie = service.findOneByName(cookies.getFirst());

        assertNotNull(cookie);
    }

    @Test
    public void findAll() {
        service.save(this.cookies);

        Iterable<Cookies> cookies = service.findAll();

        cookies.forEach(
                x -> assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void delete() {
        Cookies cookies = this.cookies.getFirst();

        service.save(cookies);
        service.delete(cookies);

        assertNull(service.findOne(cookies.getId()));
    }

    @Test
    public void deleteById() {
        Cookies cookies = this.cookies.getFirst();

        service.save(cookies);
        service.delete(cookies.getId());

        assertNull(service.findOne(cookies.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<Cookies> cookies = this.cookies;

        service.save(cookies);
        service.delete(cookies);

        cookies.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    public LinkedList<Cookies> createCookiesCollection() {
        LinkedList<Cookies> cookiesToReturn = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Cookies book = new Cookies("category " + i, "category " + i + 1);
            cookiesToReturn.add(book);
        }
        return cookiesToReturn;
    }
}