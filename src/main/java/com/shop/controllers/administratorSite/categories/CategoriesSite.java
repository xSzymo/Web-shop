package com.shop.controllers.administratorSite.categories;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administratorSite/categories")
public class CategoriesSite {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String start() {
        return "administratorSite/categoriesStartPage";
    }
}
