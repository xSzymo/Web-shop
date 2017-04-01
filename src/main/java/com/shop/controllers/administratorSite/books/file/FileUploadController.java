package com.shop.controllers.administratorSite.books.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.Pictures;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class FileUploadController {

	public static final String picturePath = "E:/WebShopPictures/";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping("upload")
	public String hey() {
		return "upload";
	}

	@RequestMapping("uploadF")
	public String heey() {
		return "uploadMultiple";
	}

	@RequestMapping("uploadFil")
	public String heey(@RequestParam("name") String name) {
		System.out.println(name);
		return "upload";
	}

	@RequestMapping(value = "/uploadFilePicture", method = RequestMethod.POST)
	public String uploadFileHandlerPicture(@RequestParam(name = "name") String name,
			@RequestParam("file") MultipartFile file, Model model) {

		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Pictures found = RepositoriesAccess.picturesRepository.findByName(name);

		if (found != null) {
			model.addAttribute("msg", "This picture already exist");
			return "administratorSite/booksCRUD/create";
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// String rootPath = System.getProperty("catalina.home");
				File dir = new File(picturePath);
				System.out.println("File : " + dir.getPath());
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println("Server : " + serverFile.getAbsolutePath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Pictures picture = new Pictures();
				picture.setName(name);
				picture.setPath(dir.getPath());
				RepositoriesAccess.picturesRepository.save(picture);

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				model.addAttribute("msg", "Success");
				model.addAttribute("pictureName", name);
				model.addAttribute("msg", "Success");
				return "administratorSite/booksCRUD/create";
			} catch (Exception e) {
				model.addAttribute("msg", "Success");
				return "administratorSite/booksCRUD/create";
			}
		} else {
			model.addAttribute("msg", "error");
		}
		return "administratorSite/booksCRUD/create";
	}

	@RequestMapping(value = "updateBook/uploadFilePictureWithId")
	public ModelAndView uploadFileHandlerPictureId(@RequestParam(name = "bookId") Long id,
			@RequestParam(name = "name") String name, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redir) {
		Iterable<Categories> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Pictures found = RepositoriesAccess.picturesRepository.findByName(name);
		Books foundBook = RepositoriesAccess.booksRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:http://localhost:8080/CRUD/administratorSite/books/updateBook/" + id);

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
				File dir = new File(picturePath);
				System.out.println("File : " + dir.getPath());
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println("Server : " + serverFile.getAbsolutePath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Pictures picture = new Pictures();
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
