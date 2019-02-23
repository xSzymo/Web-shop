package com.shop.controllers.administratorSite.books;

import com.shop.data.services.BooksService;
import com.shop.data.services.CategoriesService;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/books")
public class ReadBooks {
    @Autowired
    private BooksService booksService;
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model, HttpServletRequest request) {
        Iterable<Book> books = booksService.findAll();
        Iterable<Category> categories = categoriesService.findAll();

        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "administratorSite/booksCRUD/read";
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public String readOne(@RequestParam("name") String name, Model model) {
        Iterable<Book> books = booksService.findAll();
        Book book = booksService.findOne(name);

        if (book == null) {
            Iterable<Category> categories = categoriesService.findAll();
            model.addAttribute("msg", "not found");
            model.addAttribute("books", books);
            model.addAttribute("categories", categories);
            return "administratorSite/booksCRUD/read";
        }

        Iterable<Category> categories = categoriesService.findAll();
        for (Category x : categories)
            for (Book x1 : x.getBooks())
                if (x1.getId() == book.getId())
                    model.addAttribute("category", x);

        model.addAttribute("book", book);
        return "administratorSite/booksCRUD/read";
    }
}
