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
import java.math.BigDecimal;

@Controller
@RequestMapping("administratorSite/books")
public class CreateBooks {
    @Autowired
    private BooksService booksService;
    @Autowired
    private CategoriesService categoriesService;
//    @Autowired
//    private PicturesService picturesService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite(Model model) {
        Iterable<Category> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);

        return "administratorSite/booksCRUD/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("name") String name, @RequestParam("author") String author,
                         @RequestParam("description") String description, @RequestParam("langauge") String langauge,
                         @RequestParam("price") String price, @RequestParam("pictureName") String pictureName, Model model,
                         HttpServletRequest request) {

        Iterable<Category> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);

        String categoryName = null;
        boolean moreThanOne = false;

        for (Category x : categories) {
            if (request.getParameter(x.getName()) != null) {
                categoryName = x.getName();

                if (moreThanOne) {
                    model.addAttribute("msgError", "Check only 1 category");
                    return "administratorSite/booksCRUD/create";
                }
                moreThanOne = true;
            }
        }
        if (moreThanOne == false) {
            model.addAttribute("msgError", "You have to check 1 category");
            return "administratorSite/booksCRUD/create";
        }

        Category category = categoriesService.findOneByName(categoryName);
//        Picture picture = picturesService.findByName(pictureName);
//
//        if (picture == null) {
//            model.addAttribute("msgError", "No category and picture(optional) found");
//        }
        Book book = new Book(name, author, langauge, description, new BigDecimal(price));
//        book.getPictures().add(picture);
        booksService.save(book);
        category.getBooks().add(book);
        categoriesService.save(category);
        model.addAttribute("msgSuccess", "success");

        return "administratorSite/booksCRUD/create";
    }
}
