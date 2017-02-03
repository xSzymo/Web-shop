package com.shop.controllers.administratorSite.books;

import java.math.BigDecimal;

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
import com.shop.data.tables.Pictures;
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
		model.addAttribute("book", book);
		return "administratorSite/booksCRUD/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);
		return "administratorSite/booksCRUD/update";
	}

	/*
	 * JUST UPDATE pictures categories back to prevoius page...mistake bah
	 */
	@RequestMapping("updateBook/update")
	public String updateBook(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("language") String language,
			@RequestParam("price") String price, @RequestParam("description") String description,
			@RequestParam("category") String category, Model model) {

		Books foundBook = RepositoriesAccess.booksRepository.findById(Long.parseLong(id));
		if (foundBook == null) {
			model.addAttribute("book", foundBook);
			model.addAttribute("msg", "not found book to update");
			return "administratorSite/booksCRUD/updateOneBook";
		}
		Categories foundCategory = RepositoriesAccess.categoriesRepository.findByName(category);
		if (foundCategory == null) {
			model.addAttribute("book", foundBook);
			model.addAttribute("msg", "not category found");
			return "administratorSite/booksCRUD/updateOneBook";
		}

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

		model.addAttribute("book", foundBook);
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

		if (foundBook == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			RepositoriesAccess.booksRepository.delete(foundBook);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		Iterable<Books> books = RepositoriesAccess.booksRepository.findAll();
		model.addAttribute("books", books);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/books/delete");
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

	/*
	 * need to delete picture from disc
	 * LATER
	 */
	@RequestMapping(value = "updateBook/deletePicture")
	public ModelAndView deleteB(@RequestParam("bookId") Long bookId, @RequestParam("pictureId") Long pictureId,
			RedirectAttributes redir) {
		Books foundBook = RepositoriesAccess.booksRepository.findById(bookId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080/CRUD/administratorSite/books/updateBook/" + bookId);
		
		for(Pictures x : foundBook.getPictures()) {
			if(x.getId() == pictureId) {
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
