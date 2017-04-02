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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administratorSite/books")
public class FileUploadActions {
	
	@RequestMapping("upload")
	public String hey() {
		return "upload";
	}

	@RequestMapping("uploadFil")
	public String heey(@RequestParam("name") String name) {
		System.out.println(name);
		return "upload";
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
