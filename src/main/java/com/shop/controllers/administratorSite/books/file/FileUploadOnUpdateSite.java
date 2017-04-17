package com.shop.controllers.administratorSite.books.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Book;
import com.shop.data.tables.Category;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;

/*
 * Author : sahil0021
 * source : http://stackoverflow.com/questions/34389180/how-to-upload-file-in-server-using-springs
 */
@Controller
@RequestMapping("administratorSite/books")
public class FileUploadOnUpdateSite {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadOnUpdateSite.class);

	@RequestMapping(value = "update/uploadFileLinkId", method = RequestMethod.POST)
	public ModelAndView uploadFileHandlerLinkWithId(@RequestParam(name = "bookId") Long id,
			@RequestParam(name = "name") String name, @RequestParam("link") String link, Model model,
			RedirectAttributes redir) throws IOException {
		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Picture found = RepositoriesAccess.picturesRepository.findByName(name);
		Book foundBook = RepositoriesAccess.booksRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080" + ApplicationConfig.PROJECT_NAME + "administratorSite/books/update/" + id);

		if (found != null) {
			redir.addFlashAttribute("msgLink", "This picture already exist");
			return modelAndView;
		}

		try {
			FileUploadActions.downloadImage(link, new File(ApplicationConfig.PICTURE_PATH).getAbsolutePath(), name);

		} catch (IOException ex) {
			redir.addFlashAttribute("msgLink", "error");
			return modelAndView;
		}

		Picture picture = new Picture(name, new File(ApplicationConfig.PICTURE_PATH).getAbsolutePath() + File.separator + name);
		foundBook.getPictures().add(picture);
		RepositoriesAccess.booksRepository.save(foundBook);

		redir.addFlashAttribute("pictureLinkName", name);
		redir.addFlashAttribute("msgLink", "Success");
		return modelAndView;
	}
	
	@RequestMapping(value = "update/uploadFilePictureWithId", method = RequestMethod.POST)
	public ModelAndView uploadFileHandlerPictureId(@RequestParam(name = "bookId") Long id,
			@RequestParam(name = "name") String name, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redir) {
		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Picture found = RepositoriesAccess.picturesRepository.findByName(name);
		Book foundBook = RepositoriesAccess.booksRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080" + ApplicationConfig.PROJECT_NAME + "administratorSite/books/update/" + id);

		if (found != null) {
			redir.addFlashAttribute("msg", "This picture already exist");
			return modelAndView;
		}

		if (foundBook == null) {
			redir.addFlashAttribute("msg", "error book not found");
			return modelAndView;
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// String rootPath = System.getProperty("catalina.home");
				File dir = new File(ApplicationConfig.PICTURE_PATH);
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Picture picture = new Picture();
				picture.setName(name);
				picture.setPath(dir.getPath());

				foundBook.getPictures().add(picture);
				RepositoriesAccess.booksRepository.save(foundBook);

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				redir.addFlashAttribute("msg", "Success");
				redir.addFlashAttribute("pictureName", name);
				return modelAndView;
			} catch (Exception e) {
				redir.addFlashAttribute("msg", "Success");
				return modelAndView;
			}
		} else {
			redir.addFlashAttribute("msg", "error");
		}
		return modelAndView;
	}
}
