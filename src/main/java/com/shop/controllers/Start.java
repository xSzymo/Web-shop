package com.shop.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.net.httpserver.HttpServer;

@Controller
public class Start {

	@RequestMapping
	public String start(Model model) {
		if (!(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")))
			model.addAttribute("logged", true);
		else
			model.addAttribute("logged", false);
		return "start";
	}

	@RequestMapping("start")
	public String startSite(HttpServletRequest request, Model model) {
		for(Cookie x : request.getCookies())
			System.out.println(x.getName());
		return "start";
	}
}
