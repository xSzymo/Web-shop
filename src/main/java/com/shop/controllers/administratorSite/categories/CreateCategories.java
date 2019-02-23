package com.shop.controllers.administratorSite.categories;

import com.shop.data.services.CategoriesService;
import com.shop.data.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administratorSite/categories")
public class CreateCategories {
    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite() {
        return "administratorSite/categoriesManager/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("name") String name, Model model) {

        Category categories = categoriesService.findOne(name);

        if (categories != null)
            model.addAttribute("msgError", "Category already exist");

        Category category = new Category(name);

        categoriesService.save(category);
        model.addAttribute("msgSuccess", "success");

        return "administratorSite/categoriesManager/create";
    }
}
