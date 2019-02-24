package com.shop.controllers.shop;

import com.shop.data.services.CategoriesService;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.LinkedList;

@Controller
@RequestMapping("shop")
public class Shop {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private RememberMeServices rememberMeService;

    @SuppressWarnings("unchecked")
    public static HashSet<Book> getBasket(HttpServletRequest request) {
        if (request.getSession().getAttribute("basket") == null)
            request.getSession().setAttribute("basket", new HashSet<Book>());
        return (HashSet<Book>) request.getSession().getAttribute("basket");
    }

    @SuppressWarnings("unchecked")
    public static LinkedList<Book> getBasketWithAllBooks(HttpServletRequest request) {
        if (request.getSession().getAttribute("basketWithAllBooks") == null)
            request.getSession().setAttribute("basketWithAllBooks", new LinkedList<Book>());
        return (LinkedList<Book>) request.getSession().getAttribute("basketWithAllBooks");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String start(HttpServletRequest request, HttpServletResponse response, Model model) {

        if (request.getRequestedSessionId() != null)
            rememberMeService.autoLogin(request, response);

        if (!(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")))
            model.addAttribute("logged", true);
        else
            model.addAttribute("logged", false);

        Iterable<Category> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);
        return "shopStartPage";
    }
}
