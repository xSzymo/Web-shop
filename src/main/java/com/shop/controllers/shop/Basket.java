package com.shop.controllers.shop;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.enums.EnumPayments;
import com.shop.data.tables.Book;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

@Controller
@RequestMapping("shop")
public class Basket {

    public static BigDecimal toCalculate(LinkedList<Book> basket) {
        BigDecimal price = new BigDecimal("0");
        for (Book x : basket)
            if (x.getPrice() != null)
                price = x.getPrice().add(price);
        return price;
    }

    @RequestMapping(value = "basket", method = RequestMethod.GET)
    public String basket(Model model, HttpServletRequest request) {
        LinkedList<Book> basket = Shop.getBasketWithAllBooks(request);
        HashSet<Book> basketMain = Shop.getBasket(request);
        BigDecimal price = toCalculate(basket);

        model.addAttribute("price", price);
        model.addAttribute("basket1", basket);
        model.addAttribute("basket", basketMain);
        return "shop/basket";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public RedirectView deleteItemFromBasket(@RequestParam("id") Long id, @RequestParam("number") int number,
                                             HttpServletRequest request, Model model) {
        LinkedList<Book> basketWithAllBooks = Shop.getBasketWithAllBooks(request);
        HashSet<Book> basket = Shop.getBasket(request);
        Book book = RepositoriesAccess.booksRepository.findById(id);

        if (book == null)
            return new RedirectView(ApplicationConfig.PROJECT_NAME + "shop/basket");

        boolean is = false;
        int howMany = 1;
        int howManyBooks = 0;

        for (Book x : basketWithAllBooks)
            if (x.getId() == book.getId())
                howManyBooks++;

        if (howManyBooks < number)
            number = howManyBooks;

        while (howMany <= number)
            for (int i = 0; i < basketWithAllBooks.size(); i++) {
                if (basketWithAllBooks.get(i).getId() != null && basketWithAllBooks.get(i).getId() == book.getId()
                        && howMany <= number) {
                    basketWithAllBooks.remove(basketWithAllBooks.get(i));
                    howMany++;
                }
            }

        for (Book x : basketWithAllBooks)
            if (book.getId() == x.getId())
                is = true;

        if (!is)
            for (Book x : basket) {
                if (x.getId() == book.getId()) {
                    basket.remove(x);
                    break;
                }
            }

        request.getSession().setAttribute("basketWithAllBooks", basketWithAllBooks);
        request.getSession().setAttribute("basket", basket);

        model.addAttribute("basketWithAllBooks", basketWithAllBooks);
        model.addAttribute("basket", basket);
        return new RedirectView(ApplicationConfig.PROJECT_NAME + "shop/basket");
    }

    @RequestMapping(value = "continue", method = RequestMethod.GET)
    public String goToAcceptOrder(Model model, HttpServletRequest request) {
        LinkedList<Book> basket = Shop.getBasketWithAllBooks(request);
        EnumPayments[] kindOfPayment = EnumPayments.values();
        String[] paymentName = new String[kindOfPayment.length];
        int i = 0;
        for (EnumPayments x : kindOfPayment)
            paymentName[i++] = x.name();

        model.addAttribute("payments", paymentName);

        BigDecimal price = toCalculate(basket);
        model.addAttribute("price", price);

        model.addAttribute("basket1", basket);
        model.addAttribute("basket", request.getSession().getAttribute("basket"));

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser"))
            return "shop/options/anonymousUserOrder";

        User user = RepositoriesAccess.usersRepository
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "shop/options/userOrder";
    }
}
