package com.shop.controllers;

import java.security.Principal;
import java.security.Security;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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


	@RequestMapping
	public String start() {
		return "start";
	}

	//@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping("start")
	public String startSite(Model model) {
		if(!(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")))
				model.addAttribute("logged", false);
		else
			model.addAttribute("logged", true);
		return "start";
	}
	
	@RequestMapping("test")
	public String test(ModelMap model, Principal principal, HttpSession session) {
		System.out.println(session.getAttributeNames());
	

	     ModelAndView mav = new ModelAndView(); 
	try{
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String n= auth.getName();
	    String r= auth.getAuthorities().toString();

	     System.out.println("the value of username is "+n);
	    System.out.println("the value of role is  "+r);
	    System.out.println("||||||||||||||||||||||||\n here: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	  }
	finally {}
		return "test/test";
	}
	
	//@Secured(value = { "hasRole('ROLE_ADMIN')" })
	@RequestMapping("hey")
	public void halo() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
	}
}
