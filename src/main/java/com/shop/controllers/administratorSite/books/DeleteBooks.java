package com.shop.controllers.administratorSite.books;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.controllers.administratorSite.books.file.FileUploadActions;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Order;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class DeleteBooks {
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSite(Model model) {
		System.out.println("halo");
		Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();

		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/delete";
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public RedirectView deleteFromButton(@PathVariable Long id, Model model) {
		Book foundBook = RepositoriesAccess.booksRepository.findById(id);

		if (foundBook == null) {
			model.addAttribute("msg", "not found");
			Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
			model.addAttribute("books", books);

			return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/books/delete");
		}

		Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order x = iterator.next();
			for (Iterator<Book> iterator2 = x.getBooks().iterator(); iterator2.hasNext();) {
				Book x1 = iterator2.next();
				if (x1.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.ordersRepository.save(x);
				}
			}
		}

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
			Category x2 = iterator.next();
			for (Iterator<Book> iterator2 = x2.getBooks().iterator(); iterator2.hasNext();) {
				Book x3 = iterator2.next();
				if (x3.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.categoriesRepository.save(x2);
				}
			}
		}

		for (Picture x : foundBook.getPictures())
			FileUploadActions.deletePicture(x.getName());

		RepositoriesAccess.booksRepository.delete(foundBook.getId());

		Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);

		return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/books/delete");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteFromInputText(@RequestParam("bookName") String bookName, Model model) {
		Book foundBook = RepositoriesAccess.booksRepository.findByName(bookName);

		if (foundBook == null) {
			model.addAttribute("msg", "not found");
			Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
			model.addAttribute("books", books);

			return "/administratorSite/booksCRUD/delete";
		}

		Iterable<Order> orders = RepositoriesAccess.ordersRepository.findAll();

		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order x = iterator.next();
			for (Iterator<Book> iterator2 = x.getBooks().iterator(); iterator2.hasNext();) {
				Book x1 = iterator2.next();
				if (x1.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.ordersRepository.save(x);
				}
			}
		}

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();

		for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
			Category x2 = iterator.next();
			for (Iterator<Book> iterator2 = x2.getBooks().iterator(); iterator2.hasNext();) {
				Book x3 = iterator2.next();
				if (x3.getId() == foundBook.getId()) {
					iterator2.remove();
					RepositoriesAccess.categoriesRepository.save(x2);
				}
			}
		}

		for (Picture x : foundBook.getPictures())
			FileUploadActions.deletePicture(x.getName());

		RepositoriesAccess.booksRepository.delete(foundBook.getId());

		Iterable<Book> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);
		return "/administratorSite/booksCRUD/delete";
	}
}
