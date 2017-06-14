package com.shop.controllers.administratorSite.categories;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.tables.Category;
import com.shop.others.RepositoriesAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("administratorSite/categories")
public class DeleteCategories {

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

        model.addAttribute("categories", categories);
        return "administratorSite/categoriesManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model, RedirectAttributes red) {
        Category category = RepositoriesAccess.categoriesRepository.findById(id);

        if (category == null)
            red.addFlashAttribute("msg", "not found");
        else {
            RepositoriesAccess.categoriesRepository.delete(category);
            red.addFlashAttribute("msg", "Succes");
        }
        Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
        model.addAttribute("categories", categories);

        return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/categories/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("categoryName") String categoryName, Model model) {
        Category categoryFound = RepositoriesAccess.categoriesRepository.findByName(categoryName);

        if (categoryFound == null)
            model.addAttribute("msg", "not found");
        else {
            RepositoriesAccess.categoriesRepository.delete(categoryFound);
            model.addAttribute("msg", "Succes");
        }

        Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

        model.addAttribute("categories", categories);
        return "/administratorSite/categoriesManager/delete";
    }
}
