package com.shop.controllers.administratorSite.adress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/address")
public class ReadAddress {

	@RequestMapping("read")
	public String readSite(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("id") String id, Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();
		Address allAddress = RepositoriesAccess.addressRepository.findById(Long.parseLong(id));

		if (address == null) {
			model.addAttribute("msg", "not found");
			model.addAttribute("allAddress", allAddress);
			return "administratorSite/addressManager/read";
		}
		model.addAttribute("address", address);
		return "administratorSite/addressManager/read";
	}
}
