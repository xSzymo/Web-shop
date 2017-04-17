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

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Category;
import com.shop.data.tables.Picture;
import com.shop.others.RepositoriesAccess;

/*
 * Author : sahil0021
 * source : http://stackoverflow.com/questions/34389180/how-to-upload-file-in-server-using-springs
 */
@Controller
@RequestMapping("administratorSite/books")
public class FileUploadOnCreateSite {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadOnCreateSite.class);

	@RequestMapping(value = "/uploadFilePicture", method = RequestMethod.POST)
	public String uploadFileHandlerPicture(@RequestParam(name = "name") String name,
			@RequestParam("file") MultipartFile file, Model model) {

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Picture found = RepositoriesAccess.picturesRepository.findByName(name);

		if (found != null) {
			model.addAttribute("msg", "This picture already exist");
			return "administratorSite/booksCRUD/create";
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// String rootPath = System.getProperty("catalina.home");
				File dir = new File(ApplicationConfig.PICTURE_PATH);
				System.out.println("File : " + dir.getPath());
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println("Server : " + serverFile.getAbsolutePath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Picture picture = new Picture();
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
	
	@RequestMapping(value = "/uploadFileLink", method = RequestMethod.POST)
	public String uploadFileHandlerLink(@RequestParam(name = "name") String name, @RequestParam("link") String link,
			Model model) throws IOException {

		Iterable<Category> categories = RepositoriesAccess.categoriesRepository.findAll();
		model.addAttribute("categories", categories);

		Picture found = RepositoriesAccess.picturesRepository.findByName(name);

		if (found != null) {
			model.addAttribute("msgLink", "This picture already exist");
			return "administratorSite/booksCRUD/create";
		}
		try {
			FileUploadActions.downloadImage(link, new File(ApplicationConfig.PICTURE_PATH).getAbsolutePath(), name);

		} catch (IOException ex) {
			model.addAttribute("msgLink", "error");
			return "administratorSite/booksCRUD/create";
		}

		Picture picture = new Picture(name, new File(ApplicationConfig.PICTURE_PATH).getAbsolutePath() + File.separator + name);
		RepositoriesAccess.picturesRepository.save(picture);

		model.addAttribute("pictureLinkName", name);
		model.addAttribute("msgLink", "Success");
		return "administratorSite/booksCRUD/create";
	}
}
