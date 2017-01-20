package com.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Start {

	@RequestMapping
	public String start() {
		return "start";
	}
	
	@RequestMapping("start")
	public String startSite() {
		return "start";
	}
}
