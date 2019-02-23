package com.shop.controllers.administratorSite.orders;

import com.shop.data.enums.EnumPayments;
import com.shop.data.services.AddressService;
import com.shop.data.services.BooksService;
import com.shop.data.services.CouponCodesService;
import com.shop.data.services.OrdersService;
import com.shop.data.tables.Address;
import com.shop.data.tables.Book;
import com.shop.data.tables.CouponCode;
import com.shop.data.tables.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

@Controller
@RequestMapping("administratorSite/orders")
public class UpdateOrders {
    @Autowired
    private BooksService booksService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CouponCodesService couponCodesService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSite(Model model) {
        Iterable<Order> orders = ordersService.findAll();
        model.addAttribute("orders", orders);
        return "administratorSite/ordersManager/update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateOne(@PathVariable Long id, Model model) {
        Order foundOrder = ordersService.findOne(id);

        if (foundOrder == null)
            model.addAttribute("msg", "not found");

        EnumPayments[] kindOfPayment = EnumPayments.values();
        String[] paymentName = new String[kindOfPayment.length];
        int i = 0;
        for (EnumPayments x : kindOfPayment)
            paymentName[i++] = x.name();

        Iterable<Book> books = booksService.findAll();
        model.addAttribute("books", books);

        if (foundOrder.getPaymentMethod() != null)
            model.addAttribute("orderPayment", foundOrder.getPaymentMethod().toString());

        model.addAttribute("payments", paymentName);
        model.addAttribute("order", foundOrder);
        return "/administratorSite/ordersManager/updateOneOrder";
    }

    @RequestMapping(value = "update/updateOne", method = RequestMethod.POST)
    public String updateOrder(@RequestParam("shippingAddressStreet") String shippingAddressStreet,
                              @RequestParam("shippingAddressPostalCode") String shippingAddressPostalCode,
                              @RequestParam("shippingAddressCity") String shippingAddressCity,
                              @RequestParam("shippingAddressCountry") String shippingAddressCountry,

                              @RequestParam("billingAddressStreet") String billingAddressStreet,
                              @RequestParam("billingAddressPostalCode") String billingAddressPostalCode,
                              @RequestParam("billingAddressCity") String billingAddressCity,
                              @RequestParam("billingAddressCountry") String billingAddressCountry,

                              @RequestParam("couponCode") String couponCode,
                              @RequestParam("couponCodeDiscount") double couponCodeDiscount,

                              @RequestParam("orderId") Long orderId,

                              @RequestParam("payment") Object payment, @RequestParam("price") String price,
                              @RequestParam("realized") boolean realized, HttpServletRequest request, Model model, String... books) {

        EnumPayments[] kindOfPayment = EnumPayments.values();
        EnumPayments paymentType = null;

        for (EnumPayments x : kindOfPayment)
            if (x.name().equals(payment))
                paymentType = x;

        Order order = ordersService.findOne(orderId);
        Address billingAddress = order.getBillingAddress();
        Address shippingAddress = order.getShippingAddress();
        CouponCode couponCodes = order.getCouponCodes();

        if (billingAddress != null) {
            billingAddress.setCity(billingAddressCity);
            billingAddress.setCountry(billingAddressCountry);
            billingAddress.setPostalCode(billingAddressPostalCode);
            billingAddress.setStreet(billingAddressStreet);
        } else {
            billingAddress = new Address();
            billingAddress.setCity(billingAddressCity);
            billingAddress.setCountry(billingAddressCountry);
            billingAddress.setPostalCode(billingAddressPostalCode);
            billingAddress.setStreet(billingAddressStreet);
        }

        if (shippingAddress != null) {
            shippingAddress.setCity(shippingAddressCity);
            shippingAddress.setCountry(shippingAddressCountry);
            shippingAddress.setPostalCode(shippingAddressPostalCode);
            shippingAddress.setStreet(shippingAddressStreet);
        } else {
            shippingAddress = new Address();
            shippingAddress.setCity(shippingAddressCity);
            shippingAddress.setCountry(shippingAddressCountry);
            shippingAddress.setPostalCode(shippingAddressPostalCode);
            shippingAddress.setStreet(shippingAddressStreet);
        }

        if (couponCodes != null) {
            couponCodes.setCode(couponCode);
            couponCodes.setCodeDiscount(couponCodeDiscount);
        } else {
            couponCodes = new CouponCode();
            couponCodes.setCode(couponCode);
            couponCodes.setCodeDiscount(couponCodeDiscount);
            order.setCouponCodes(couponCodes);
        }
        addressService.save(billingAddress);
        addressService.save(shippingAddress);
        couponCodesService.save(couponCodes);

        order.setPrice(new BigDecimal(price));
        order.setRealized(realized);
        order.setPaymentMethod(paymentType);
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);

        ArrayList<Book> b = new ArrayList<Book>();

        for (int i = 0; i < books.length; i++) {
            if (booksService.findOne(books[i]) != null) {
                b.add(booksService.findOne(books[i]));
            }
        }

        booksService.save(b);

        order.getBooks().addAll(b);
        ordersService.save(order);

        // if(order.getPaymentMethod() != null)
        // model.addAttribute("orderPayment",
        // order.getPaymentMethod().toString());
        model.addAttribute("msg", "Success");
        return updateOne(order.getId(), model);
    }

    @RequestMapping(value = "update/createBooks", method = RequestMethod.POST)
    public String createBook(@RequestParam("orderId") Long id, Model model, HttpServletRequest request) {

        Iterable<Book> allBooks = booksService.findAll();
        LinkedList<Book> chosenBooks = new LinkedList<Book>();
        chosenBooks.clear();

        for (Book x : allBooks)
            if (request.getParameter(x.getName()) != null)
                chosenBooks.add(x);

        Order order = ordersService.findOne(id);
        order.setBooks(chosenBooks);

        ordersService.save(order);

        return updateOne(id, model);
    }
}
