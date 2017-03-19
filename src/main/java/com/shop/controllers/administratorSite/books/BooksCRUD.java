package com.shop.controllers.administratorSite.books;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Pictures;
import com.shop.others.RepositoriesAccess;

/*
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
 * YES ITS HEREYES ITS HEREYES ITS HEREYES ITS HEREYES ITS HERE
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
	public String createSite(Model model) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping("/createBook")
	public String create1(@RequestParam("name") String name, @RequestParam("author") String author,
			@RequestParam("description") String description, @RequestParam("langauge") String langauge,
			@RequestParam("price") String price, @RequestParam("pictureName") String pictureName, Model model,
			HttpServletRequest request) {

		Pictures picture = RepositoriesAccess.picturesRepository.findByName(pictureName);

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		String categoryName = null;
		boolean moreThanOne = false;

		for (Categories x : categories) {
			if (request.getParameter(x.getName()) != null) {
				categoryName = x.getName();

				if (moreThanOne) {
					model.addAttribute("msgError", "Check only 1 category");
					return "administratorSite/booksCRUD/create";
				}
				moreThanOne = true;
			}
		}
		if (moreThanOne == false) {
			model.addAttribute("msgError", "You have to check 1 category");
			return "administratorSite/booksCRUD/create";
		}

		Categories category = RepositoriesAccess.categoriesRepository.findByName(categoryName);

		if (picture == null) {
			model.addAttribute("msgError", "No category and picture(optional) found");
		}
		Books book = new Books(name, author, langauge, description, new BigDecimal(price));
		book.getPictures().add(picture);
		RepositoriesAccess.booksRepository.save(book);
		category.getBooks().add(book);
		RepositoriesAccess.categoriesRepository.save(category);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("books", books);
		model.addAttribute("categories", categories);

		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("name") String name, Model model) {
		Books book = RepositoriesAccess.booksRepository.findByName(name);

		if (book == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/booksCRUD/read";
		}
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		for (Categories x : categories)
			for (Books x1 : x.getBooks())
				if (x1.getId() == book.getId()) {
					model.addAttribute("category", x);
				}

		model.addAttribute("book", book);
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		Iterable<Categories> allCategories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("books", books);
		model.addAttribute("categories", allCategories);
		return "administratorSite/booksCRUD/update";
	}

	/*
	 * JUST UPDATE pictures categories back to prevoius page...mistake bah
	 */
	@RequestMapping("updateBook/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("language") String language,
			@RequestParam("price") String price, @RequestParam("description") String description, Model model,
			HttpServletRequest request) {

		Iterable<Categories> allCategories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", allCategories);

		Books foundBook = RepositoriesAccess.booksRepository.findById(Long.parseLong(id));
		if (foundBook == null) {
			model.addAttribute("book", foundBook);
			model.addAttribute("msg", "not found book to update");
			return "administratorSite/booksCRUD/updateOneBook";
		}
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		String categoryName = null;
		boolean moreThanOne = false;

		for (Categories x : categories) {
			if (request.getParameter(x.getName()) != null) {
				categoryName = x.getName();

				if (moreThanOne) {
					model.addAttribute("msgError", "Check only 1 category");
					return "administratorSite/booksCRUD/create";
				}
				moreThanOne = true;
			}
		}
		if (moreThanOne == false) {
			model.addAttribute("msgError", "You have to check 1 category");
			return "administratorSite/booksCRUD/create";
		}

		Categories foundCategory = RepositoriesAccess.categoriesRepository.findByName(categoryName);

		foundBook.setName(name);
		foundBook.setAuthor(author);
		foundBook.setPrice(new BigDecimal(price));
		foundBook.setDescription(description);
		foundBook.setLanguage(language);
		RepositoriesAccess.booksRepository.save(foundBook);

		foundCategory.getBooks().add(foundBook);
		RepositoriesAccess.categoriesRepository.save(foundCategory);
		
		model.addAttribute("book", foundBook);
		model.addAttribute("msg", "Success");
		return "administratorSite/booksCRUD/updateOneBook";
	}

	@RequestMapping(value = "updateBook/{bookId}")
	public String updateBook(@PathVariable Long bookId, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);

		if (foundBook == null)
			model.addAttribute("msg", "not found");

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();

		model.addAttribute("book", foundBook);
		model.addAttribute("categories", categories);
		return "/administratorSite/booksCRUD/updateOneBook";
	}

	// 2
	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();

		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/delete";
	}

	@RequestMapping(value = "deleteBook/{bookId}")
	public RedirectView deleteB(@PathVariable Long bookId, Model model) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);

		if (foundBook == null) {
			model.addAttribute("msg", "not found");
			Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
			model.addAttribute("books", books);

			return new RedirectView(ApplicationConfig.projectName + "administratorSite/books/delete");
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

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/books/delete");
		/*
		 * Iterable<Orders> orders =
		 * RepositoriesAccess.ordersRepository.findAll();
		 * 
		 * for(Orders x : orders) for(Books x1 : x.getBooks()) if(x1.getId() ==
		 * foundBook.getId()) { x.getBooks().remove(x1);
		 * RepositoriesAccess.ordersRepository.save(x); }
		 * 
		 * Iterable<Categories> categories =
		 * RepositoriesAccess.categoriesRepository.findAll();
		 * 
		 * for(Categories x2 : categories) { for(Books x3 : x2.getBooks()) {
		 * if(x3.getId() == foundBook.getId()) { x2.getBooks().remove(x3);
		 * RepositoriesAccess.categoriesRepository.save(x2); } } }
		 * RepositoriesAccess.booksRepository.delete(foundBook.getId());
		 */
	}

	@RequestMapping(value = "deleteBook")
	public String deleteB(@RequestParam("bookName") String bookName, Model model) {
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

	/*
	 * need to delete picture from disc LATER
	 */
	@RequestMapping(value = "updateBook/deletePicture")
	public ModelAndView deleteB(@RequestParam("bookId") Long bookId, @RequestParam("pictureId") Long pictureId,
			RedirectAttributes redir) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080/CRUD/administratorSite/books/updateBook/" + bookId);

		for (Pictures x : foundBook.getPictures()) {
			if (x.getId() == pictureId) {
				Pictures foundPicture = RepositoriesAccess.picturesRepository.findById(pictureId);
				foundBook.getPictures().remove(foundPicture);
				RepositoriesAccess.booksRepository.save(foundBook);
				RepositoriesAccess.picturesRepository.delete(foundPicture);
				redir.addFlashAttribute("msg", "Success");
				return modelAndView;
			}
		}
		redir.addFlashAttribute("msg", "Error with delete picture");
		return modelAndView;
	}
}
