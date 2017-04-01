package com.shop.controllers.administratorSite.books.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Pictures;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class FileUploadController1 {

	public static final String picturePath = "E:/WebShopPictures/";

	@RequestMapping(value = "/uploadFileLink")
	public String uploadFileHandlerLink(@RequestParam(name = "name") String name, @RequestParam("link") String link,
			Model model) throws IOException {

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Pictures found = RepositoriesAccess.picturesRepository.findByName(name);

		if (found != null) {
			model.addAttribute("msgLink", "This picture already exist");
			return "administratorSite/booksCRUD/create";
		}

		try {
			downloadImage(link, new File(picturePath).getAbsolutePath(), name);

		} catch (IOException ex) {
			model.addAttribute("msgLink", "error");
			return "administratorSite/booksCRUD/create";
		}

		Pictures picture = new Pictures(name, new File(picturePath).getAbsolutePath() + File.separator + name);
		RepositoriesAccess.picturesRepository.save(picture);

		model.addAttribute("pictureLinkName", name);
		model.addAttribute("msgLink", "Success");
		return "administratorSite/booksCRUD/create";
	}

	public static void downloadImage(String sourceUrl, String targetDirectory, String name)
			throws MalformedURLException, IOException, FileNotFoundException {
		URL imageUrl = new URL(sourceUrl);
		try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
				OutputStream imageWriter = new BufferedOutputStream(
						new FileOutputStream(targetDirectory + File.separator + name));) {
			int readByte;
			// System.out.println(FilenameUtils.getName(sourceUrl));
			while ((readByte = imageReader.read()) != -1) {
				imageWriter.write(readByte);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "updateBook/uploadFileLinkId")
	public ModelAndView uploadFileHandlerLinkWithId(@RequestParam(name = "bookId") Long id,
			@RequestParam(name = "name") String name, @RequestParam("link") String link, Model model,
			RedirectAttributes redir) throws IOException {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Pictures found = RepositoriesAccess.picturesRepository.findByName(name);
		Books foundBook = RepositoriesAccess.booksRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080/CRUD/administratorSite/books/updateBook/" + id);

		if (found != null) {
			redir.addFlashAttribute("msgLink", "This picture already exist");
			return modelAndView;
		}

		try {
			downloadImage(link, new File(picturePath).getAbsolutePath(), name);

		} catch (IOException ex) {
			redir.addFlashAttribute("msgLink", "error");
			return modelAndView;
		}

		Pictures picture = new Pictures(name, new File(picturePath).getAbsolutePath() + File.separator + name);
		foundBook.getPictures().add(picture);
		RepositoriesAccess.booksRepository.save(foundBook);

		redir.addFlashAttribute("pictureLinkName", name);
		redir.addFlashAttribute("msgLink", "Success");
		return modelAndView;
	}
}
