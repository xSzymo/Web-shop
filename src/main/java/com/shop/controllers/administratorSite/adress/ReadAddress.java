package com.shop.controllers.administratorSite.adress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/address")
public class ReadAddress {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/read";
	}

	@RequestMapping(value = "readOne", method = RequestMethod.GET)
	public String readOne(@RequestParam("id") String id, Model model) {
		Iterable<Address> allAddress = RepositoriesAccess.addressRepository.findAll();
		Address address = RepositoriesAccess.addressRepository.findById(Long.parseLong(id));

		if (allAddress == null) {
			model.addAttribute("msg", "not found");
			model.addAttribute("allAddress", allAddress);
			return "administratorSite/addressManager/read";
		}
		model.addAttribute("address", address);
		return "administratorSite/addressManager/read";
	}
}
