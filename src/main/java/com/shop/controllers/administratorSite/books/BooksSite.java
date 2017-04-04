package com.shop.controllers.administratorSite.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administratorSite/books")
public class BooksSite {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String start() {
		return "administratorSite/booksStartPage";
	}
}
