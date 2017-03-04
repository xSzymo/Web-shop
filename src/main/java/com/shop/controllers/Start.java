package com.shop.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.data.repositories.CategoriesRepository;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;
import com.sun.glass.ui.View;

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
	public ModelAndView test(RedirectAttributes redir){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("redirect:test123");
	    redir.addFlashAttribute("msg", "mesedz");
	    return modelAndView;
	}
	
	@RequestMapping("test1")
	public String test123() {
		
		return "test/test";
	}
	
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping("secured")
	public void sdasacxsxz() {
		System.out.println("secured");
	}
}
