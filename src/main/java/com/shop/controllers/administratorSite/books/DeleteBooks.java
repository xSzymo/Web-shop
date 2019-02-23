package com.shop.controllers.administratorSite.books;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.services.BooksService;
import com.shop.data.tables.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("administratorSite/books")
public class DeleteBooks {
    @Autowired
    private BooksService booksService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        System.out.println("halo");
        Iterable<Book> books = booksService.findAll();

        model.addAttribute("books", books);
        return "administratorSite/booksCRUD/delete";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model) {
        Book foundBook = booksService.findOne(id);

        if (foundBook == null) {
            model.addAttribute("msg", "not found");
            Iterable<Book> books = booksService.findAll();
            model.addAttribute("books", books);

            return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/books/delete");
        }
        booksService.delete(foundBook.getId());

        Iterable<Book> books = booksService.findAll();
        model.addAttribute("books", books);

        return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/books/delete");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("bookName") String bookName, Model model) {
        Book foundBook = booksService.findOne(bookName);

        if (foundBook == null) {
            model.addAttribute("msg", "not found");
            Iterable<Book> books = booksService.findAll();
            model.addAttribute("books", books);

            return "/administratorSite/booksCRUD/delete";
        }
        booksService.delete(foundBook.getId());

        Iterable<Book> books = booksService.findAll();
        model.addAttribute("books", books);
        return "/administratorSite/booksCRUD/delete";
    }
}
