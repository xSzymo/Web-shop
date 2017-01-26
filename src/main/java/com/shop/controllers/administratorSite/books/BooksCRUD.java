package com.shop.controllers.administratorSite.books;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.data.operations.CategoriesOperations;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Pictures;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

/*
 * add a few picture to 1 book
 * 2.make http request with diffrent url which should be
 */
@Controller
@RequestMapping("administratorSite/books")
public class BooksCRUD {

	@RequestMapping
	public String start() {
		return "administratorSite/booksStartPage";
	}

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping("/createBook")
	public String create1(@RequestParam("name") String name, @RequestParam("author") String author,
			@RequestParam("description") String description, @RequestParam("langauge") String langauge,
			@RequestParam("price") String price, @RequestParam("categoryName") String categoryName,
			@RequestParam("pictureName") String pictureName, Model model) {

		Categories category = RepositoriesAccess.categoriesRepository.findByName(categoryName);
		Pictures picture = RepositoriesAccess.picturesRepository.findByName(pictureName);

		if (category == null) {
			model.addAttribute("msgError", "No category found");
			if (picture == null)
				model.addAttribute("msgError", "No category and picture(optional) found");
			return "administratorSite/booksCRUD/create";
		} else if (picture == null)
			model.addAttribute("msgError", "No picture(optional) found");

		Books book = new Books(name, author, langauge, description, new BigDecimal(price));
		book.getPictures().add(picture);
		category.getBooks().add(book);
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();

		model.addAttribute("books", books);

	    String referrer = request.getHeader("Referer");
	    request.getSession().setAttribute("url_prior_login", referrer);
		
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("name") String name, Model model) {
		Books book = RepositoriesAccess.booksRepository.findByName(name);

		if (book == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/booksCRUD/read";
		}
		model.addAttribute("book", book);
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/update";
	}

	@RequestMapping("updateBook/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("language") String language,
			@RequestParam("price") String price, @RequestParam("description") String description,
			@RequestParam("category") String category, Model model) {

		System.out.println("hej");
		Books foundBook = RepositoriesAccess.booksRepository.findById(Long.parseLong(id));
		if (foundBook == null) {
			model.addAttribute("msg", "not found book to update");
			return "AdministratorSite/booksCRUD/update";
		}
		Categories foundCategory = RepositoriesAccess.categoriesRepository.findByName(category);
		if(foundCategory == null) {
			model.addAttribute("msg", "not category found");
			return "AdministratorSite/booksCRUD/update";			
		}
		foundBook.setName(name);
		foundBook.setAuthor(author);
		foundBook.setPrice(new BigDecimal(price));
		foundBook.setDescription(description);
		foundBook.setLanguage(language);
		foundCategory.getBooks().add(foundBook);
		RepositoriesAccess.categoriesRepository.save(foundCategory);

		return "administratorSite/booksCRUD/update";
	}

	@RequestMapping(value = "updateBook/{bookId}")
	public String updateBook(@PathVariable Long bookId, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);

		if (foundBook == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("book", foundBook);
		return "/administratorSite/booksCRUD/update1";
	}

	//2
	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();

		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/delete";
	}

	@RequestMapping(value = "deleteBook/{bookId}")
	public String deleteB(@PathVariable Long bookId, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);

		if (foundBook == null)
			model.addAttribute("msg", "not found");
		else {
			RepositoriesAccess.booksRepository.delete(foundBook);
			model.addAttribute("msg", "Succes");
		}

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);

		return "/administratorSite/booksCRUD/delete";
	}

	@RequestMapping(value = "deleteBook")
	public String deleteB(@RequestParam("bookName") String bookName, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findByName(bookName);

		if (foundBook == null)
			model.addAttribute("msg", "not found");
		else {
			RepositoriesAccess.booksRepository.delete(foundBook);
			model.addAttribute("msg", "Succes");
		}

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);
		return "/administratorSite/booksCRUD/delete";
	}
}
