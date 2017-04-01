package com.shop.controllers.administratorSite.adress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/address")
public class UpdateAddress {

	@RequestMapping("/update")
	public String updateSite(Model model) {
		Iterable<Address> address = RepositoriesAccess.addressRepository.findAll();

		model.addAttribute("allAddress", address);
		return "administratorSite/addressManager/update";
	}
	
	@RequestMapping(value = "updateAddress/{addressId}")
	public String updateOneSite(@PathVariable Long addressId, Model model) {
		Address address = RepositoriesAccess.addressRepository.findById(addressId);

		if (address == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("address", address);
		return "/administratorSite/addressManager/updateOneAddress";
	}

	@RequestMapping("updateAddress/update")
	public String update(@RequestParam("city") String city, @RequestParam("country") String country,
			@RequestParam("postalCode") String postalCode, @RequestParam("street") String street,
			@RequestParam("id") String id, Model model) {

		Address address = RepositoriesAccess.addressRepository.findById(Long.parseLong(id));

		if (address == null) {
			model.addAttribute("msg", "not found category to update");
			return "administratorSite/couponCodesManager/updateOneAddress";
		}

		address.setCity(city);
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setStreet(street);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("msg", "Success");
		return "administratorSite/addressManager/updateOneAddress";
	}
}
