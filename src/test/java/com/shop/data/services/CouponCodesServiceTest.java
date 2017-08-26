package com.shop.data.services;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.tables.CouponCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class CouponCodesServiceTest extends DataBaseTestConfiguration {
    @Autowired
    private CouponCodesService service;

    private LinkedList<CouponCode> couponCodes;

    @Before
    public void BeforeEachTest() {
        couponCodes = createCouponCodesCollection();
    }

    @After
    public void afterEachTest() {
        service.delete(couponCodes);
    }

    @Test
    public void save() {
        CouponCode couponCode = couponCodes.getFirst();

        service.save(couponCode);

        assertTrue(couponCode.equals(service.findOne(couponCode.getId())));
    }

    @Test
    public void saveOne() {
        CouponCode couponCode = couponCodes.getFirst();

        service.save(couponCode);

        assertTrue(couponCode.equals(service.findOne(couponCode.getId())));
    }

    @Test
    public void saveCollection() {
        LinkedList<CouponCode> couponCodes = this.couponCodes;

        service.save(couponCodes);

        couponCodes.forEach(
                x -> assertTrue(x.equals(service.findOne(x.getId())))
        );
    }

    @Test
    public void saveNull() {
        try {
            service.save((CouponCode) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void saveOneWithExistName() {
        couponCodes.add(new CouponCode(2D, "category"));
        couponCodes.add(new CouponCode(2D, "category"));
        service.save(couponCodes);

        for (CouponCode x : service.findAll()) {
            int numberOfSameObject = 0;
            for (CouponCode x1 : service.findAll()) {
                if (x.getCode().equals(x1.getCode()))
                    numberOfSameObject++;
                if (numberOfSameObject > 1)
                    fail("can't save two categories with same name");
            }
        }
    }

    @Test
    public void findOne() {
        service.save(couponCodes.getFirst());

        CouponCode couponCodes = service.findOne(this.couponCodes.getFirst());

        assertNotNull(couponCodes);
    }

    @Test
    public void findOneWithNull() {
        try {
            service.findOne((CouponCode) null);
            service.findOneByCode((CouponCode) null);
            service.findOne(null);
            service.findOneByCode((String) null);
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void findOneById() {
        service.save(couponCodes.getFirst());

        CouponCode couponCode = service.findOne(couponCodes.getFirst().getId());

        assertNotNull(couponCode);
    }

    @Test
    public void findDoesNotExistObject() {
        service.save(couponCodes.getFirst());

        CouponCode couponCode = service.findOneByCode(new CouponCode());

        assertNull(couponCode);
    }

    @Test
    public void findOneByNameWithString() {
        service.save(couponCodes.getFirst());

        CouponCode couponCode = service.findOneByCode(couponCodes.getFirst().getCode());

        assertNotNull(couponCode);
    }

    @Test
    public void findOneByNameWithObject() {
        service.save(couponCodes.getFirst());

        CouponCode couponCode = service.findOneByCode(couponCodes.getFirst());

        assertNotNull(couponCode);
    }

    @Test
    public void findAll() {
        service.save(this.couponCodes);

        Iterable<CouponCode> couponCodes = service.findAll();

        couponCodes.forEach(
                x -> assertNotNull(service.findOne(x.getId()))
        );
    }

    @Test
    public void delete() {
        CouponCode couponCode = couponCodes.getFirst();

        service.save(couponCode);
        service.delete(couponCode);

        assertNull(service.findOne(couponCode.getId()));
    }

    @Test
    public void deleteById() {
        CouponCode couponCode = couponCodes.getFirst();

        service.save(couponCode);
        service.delete(couponCode.getId());

        assertNull(service.findOne(couponCode.getId()));
    }

    @Test
    public void deleteCollection() {
        LinkedList<CouponCode> couponCodes = this.couponCodes;

        service.save(couponCodes);
        service.delete(couponCodes);

        couponCodes.forEach(
                x -> assertNull(service.findOne(x.getId()))
        );
    }

    public LinkedList<CouponCode> createCouponCodesCollection() {
        LinkedList<CouponCode> couponCodes = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            CouponCode book = new CouponCode(3D, "category " + i);
            couponCodes.add(book);
        }
        return couponCodes;
    }
}


