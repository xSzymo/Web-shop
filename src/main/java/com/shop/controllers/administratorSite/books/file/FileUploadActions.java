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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Pictures;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/books")
public class FileUploadActions {

	@RequestMapping("upload")
	public String upload() {
		return "upload";
	}

	@RequestMapping(value = "uploadFil", method = RequestMethod.POST)
	public String uploadFIle(@RequestParam("name") String name) {
		return "upload";
	}

	public static void deletePicture(String name) {
		try {
			Pictures picture = RepositoriesAccess.picturesRepository.findByName(name);
			if (picture != null)
				RepositoriesAccess.picturesRepository.delete(picture.getId());

			File file = new File(ApplicationConfig.PICTURE_PATH + name);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadImage(String sourceUrl, String targetDirectory, String name)
			throws MalformedURLException, IOException, FileNotFoundException {
		URL imageUrl = new URL(sourceUrl);
		try (InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
				OutputStream imageWriter = new BufferedOutputStream(
						new FileOutputStream(targetDirectory + File.separator + name));) {
			int readByte;
			while ((readByte = imageReader.read()) != -1) {
				imageWriter.write(readByte);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
