package com.shop.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String startSite(Model model) {
		return "start";
	}
}
