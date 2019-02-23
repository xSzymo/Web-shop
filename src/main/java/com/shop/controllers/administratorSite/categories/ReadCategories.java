package com.shop.controllers.administratorSite.categories;

import com.shop.data.services.CategoriesService;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/categories")
public class ReadCategories {
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model, HttpServletRequest request) {
        Iterable<Category> categories = categoriesService.findAll();

        model.addAttribute("categories", categories);
        return "administratorSite/categoriesManager/read";
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public String readOne(@RequestParam("name") String name, Model model) {
        Iterable<Category> categories = categoriesService.findAll();
        Category category = categoriesService.findOneByName(name);

        if (category == null) {
            model.addAttribute("msg", "not found");
            model.addAttribute("categories", categories);
            return "administratorSite/categoriesManager/read";
        }
        model.addAttribute("category", category);
        return "administratorSite/categoriesManager/read";
    }
}
