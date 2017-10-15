package com.shop.controllers.shop.actions;

import com.shop.controllers.shop.Basket;
import com.shop.controllers.shop.Shop;
import com.shop.data.enums.EnumPayments;
import com.shop.data.services.AddressService;
import com.shop.data.services.CouponCodesService;
import com.shop.data.services.OrdersService;
import com.shop.data.services.UsersService;
import com.shop.data.tables.*;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

public class OrderActions {
    @Autowired
    private static AddressService addressService;
    @Autowired
    private static CouponCodesService couponCodesService;
    @Autowired
    private static UsersService usersService;
    @Autowired
    private static OrdersService ordersService;

    public static String saveOrderAndReturnMessage(String shippingAddressStreet, String shippingAddressPostalCode,
                                                   String shippingAddressCity, String shippingAddressCountry, String billingAddressStreet,
                                                   String billingAddressPostalCode, String billingAddressCity, String billingAddressCountry, Object payment,
                                                   String couponCode, String eMail, HttpServletRequest request) {
        LinkedList<Book> secondBasket = Shop.getBasketWithAllBooks(request);
        HashSet<Book> basket = Shop.getBasket(request);
        BigDecimal price = Basket.toCalculate(secondBasket);

        EnumPayments[] kindOfPayment = EnumPayments.values();
        EnumPayments paymentType = null;

        for (EnumPayments x : kindOfPayment)
            if (x.name().equals(payment))
                paymentType = x;
        Address shippingAddress = new Address(shippingAddressStreet, shippingAddressPostalCode, shippingAddressCity,
                shippingAddressCountry);
        Address billingAddress = new Address(billingAddressStreet, billingAddressPostalCode, billingAddressCity,
                billingAddressCountry);
        addressService.save(shippingAddress);
        addressService.save(billingAddress);

        CouponCode coupon = couponCodesService.findOneByCode(couponCode);
        if (coupon != null)
            couponCodesService.save(coupon);
        else
            coupon = null;

        Order order = new Order();
        order.setBillingAddress(shippingAddress);
        order.setShippingAddress(billingAddress);
        order.setPaymentMethod(paymentType);
        order.setCouponCodes(coupon);
        order.setPrice(price);
        ordersService.save(order);

        String text = null;

        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            User user = usersService
                    .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            user.getOrders().add(order);
            usersService.save(user);

            text = EmailText.textFromUser(price, paymentType, shippingAddressStreet, shippingAddressPostalCode,
                    shippingAddressCity, shippingAddressCountry, billingAddressStreet, billingAddressPostalCode,
                    billingAddressCity, billingAddressCountry, user.getLogin(), basket);
        } else {
            text = EmailText.textFromAnonymous(price, paymentType, shippingAddressStreet, shippingAddressPostalCode,
                    shippingAddressCity, shippingAddressCountry, billingAddressStreet, billingAddressPostalCode,
                    billingAddressCity, billingAddressCountry, basket);
        }
        return text;
    }
}
