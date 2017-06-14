package com.shop.controllers.administratorSite.books;

import com.shop.configuration.ApplicationProperties;
import com.shop.controllers.administratorSite.books.file.FileUploadActions;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("administratorSite/books")
public class UpdateBooks {

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public static String updateOneSite(@PathVariable Long id, Model model) {
        Book foundBook = RepositoriesAccess.booksRepository.findById(id);

        if (foundBook == null)
            model.addAttribute("msg", "not found");

        Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

        model.addAttribute("book", foundBook);
        model.addAttribute("categories", categories);
        return "/administratorSite/booksCRUD/updateOneBook";
    }

    @SuppressWarnings("unused")
    public static boolean isNumber(String str) {
        try {
            double number = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSite(Model model) {
        Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
        Iterable<Category> allCategories = RepositoriesAccess.categoriesRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("categories", allCategories);
        return "administratorSite/booksCRUD/update";
    }

    @RequestMapping(value = "update/updateOne", method = RequestMethod.POST)
    public String updateBook(@RequestParam("id") String id, @RequestParam("name") String name,
                             @RequestParam("author") String author, @RequestParam("language") String language,
                             @RequestParam("price") String price, @RequestParam("description") String description, Model model,
                             HttpServletRequest request) {
        Iterable<Category> allCategories = RepositoriesAccess.categoriesRepository.findAll();
        model.addAttribute("categories", allCategories);

        Book foundBook = RepositoriesAccess.booksRepository.findById(Long.parseLong(id));

        if (foundBook == null) {
            model.addAttribute("book", foundBook);
            model.addAttribute("msg", "not found book to update");
            return "administratorSite/booksCRUD/updateOneBook";
        }
        Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
        String categoryName = null;
        boolean moreThanOne = false;

        for (Category x : categories) {
            if (request.getParameter(x.getName()) != null) {
                categoryName = x.getName();

                if (moreThanOne) {
                    model.addAttribute("msg", "Check only 1 category");
                    return "administratorSite/booksCRUD/updateOneBook";
                }
                moreThanOne = true;
            }
        }
        if (moreThanOne == false) {
            model.addAttribute("msg", "You have to check 1 category");
            return "administratorSite/booksCRUD/updateOneBook";
        }
        foundBook.setName(name);
        foundBook.setAuthor(author);
        foundBook.setDescription(description);
        foundBook.setLanguage(language);
        if (isNumber(price))
            foundBook.setPrice(new BigDecimal(price));
        else
            foundBook.setPrice(null);
        RepositoriesAccess.booksRepository.save(foundBook);

        Category foundCategory = RepositoriesAccess.categoriesRepository.findByName(categoryName);
        foundCategory.getBooks().add(foundBook);
        RepositoriesAccess.categoriesRepository.save(foundCategory);

        model.addAttribute("book", foundBook);
        model.addAttribute("msg", "Success");
        return "administratorSite/booksCRUD/updateOneBook";
    }

    @RequestMapping(value = "update/deletePicture", method = RequestMethod.POST)
    public ModelAndView deletePicture(@RequestParam("bookId") Long bookId, @RequestParam("pictureId") Long pictureId,
                                      RedirectAttributes red) {
        Book foundBook = RepositoriesAccess.booksRepository.findById(bookId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + ApplicationProperties.URL + ApplicationProperties.PROJECT_NAME
                + "administratorSite/books/update/" + bookId);

        for (Picture x : foundBook.getPictures()) {
            if (x.getId() == pictureId) {
                Picture foundPicture = RepositoriesAccess.picturesRepository.findById(pictureId);
                foundBook.getPictures().remove(foundPicture);
                RepositoriesAccess.booksRepository.save(foundBook);
                RepositoriesAccess.picturesRepository.delete(foundPicture);
                FileUploadActions.deletePicture(foundPicture.getName());
                red.addFlashAttribute("msg", "Success");
                return modelAndView;
            }
        }
        red.addFlashAttribute("msg", "Error with delete picture");
        return modelAndView;
    }
}
