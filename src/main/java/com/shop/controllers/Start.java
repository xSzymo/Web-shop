package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
public class Start {
	public static CategoriesRepository categoriesRepository;
	
	@Autowired
	public Start(CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}

	@RequestMapping
	public String start() {
		return "start";
	}
	
	@RequestMapping("start")
	public String startSite() {
		return "start";
	}
	
	@RequestMapping("test")
	public String test() {
		
		return "test/test";
	}
}
