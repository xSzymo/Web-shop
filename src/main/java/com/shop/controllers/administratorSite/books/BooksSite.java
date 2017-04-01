package com.shop.controllers.administratorSite.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administratorSite/books")
public class BooksSite {

	@RequestMapping
	public String start() {
		return "administratorSite/booksStartPage";
	}
}
