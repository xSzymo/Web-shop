package com.shop.controllers.administratorSite.books;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Orders;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class DeleteBooks {

	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();

		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/delete";
	}

	@RequestMapping(value = "deleteBook/{bookId}")
	public RedirectView deleteFromButton(@PathVariable Long bookId, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);

		if (foundBook == null) {
			model.addAttribute("msg", "not found");
			Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
			model.addAttribute("books", books);

			return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/books/delete");
		}

		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();

		for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
			Orders x = iterator.next();
			for (Iterator<Books> iterator2 = x.getBooks().iterator(); iterator2.hasNext();) {
				Books x1 = iterator2.next();
				if (x1.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.ordersRepository.save(x);
				}
			}
		}

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		for (Iterator<Categories> iterator = categories.iterator(); iterator.hasNext();) {
			Categories x2 = iterator.next();
			for (Iterator<Books> iterator2 = x2.getBooks().iterator(); iterator2.hasNext();) {
				Books x3 = iterator2.next();
				if (x3.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.categoriesRepository.save(x2);
				}
			}
		}

		RepositoriesAccess.booksRepository.delete(foundBook.getId());

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);

		return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/books/delete");
	}

	@RequestMapping(value = "deleteBook")
	public String deleteFromInputText(@RequestParam("bookName") String bookName, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findByName(bookName);

		if (foundBook == null) {
			model.addAttribute("msg", "not found");
			Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
			model.addAttribute("books", books);

			return "/administratorSite/booksCRUD/delete";
		}

		Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();

		for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
			Orders x = iterator.next();
			for (Iterator<Books> iterator2 = x.getBooks().iterator(); iterator2.hasNext();) {
				Books x1 = iterator2.next();
				if (x1.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.ordersRepository.save(x);
				}
			}
		}

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		for (Iterator<Categories> iterator = categories.iterator(); iterator.hasNext();) {
			Categories x2 = iterator.next();
			for (Iterator<Books> iterator2 = x2.getBooks().iterator(); iterator2.hasNext();) {
				Books x3 = iterator2.next();
				if (x3.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.categoriesRepository.save(x2);
				}
			}
		}

		RepositoriesAccess.booksRepository.delete(foundBook.getId());

		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);
		return "/administratorSite/booksCRUD/delete";
	}
}
