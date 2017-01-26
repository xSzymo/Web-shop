package com.shop.others;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.controllers.administratorSite.books.FileUploadController;

@Controller
public class ShowPictures {
	
	@RequestMapping(value = "/getImage/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageName, HttpServletRequest request) throws IOException {
		String rpath;
		rpath = FileUploadController.picturePath + imageName; 
		Path path = Paths.get(rpath);
		byte[] data = Files.readAllBytes(path);
		return data;
	}
}
