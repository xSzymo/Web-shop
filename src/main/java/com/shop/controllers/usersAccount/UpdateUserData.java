package com.shop.controllers.usersAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.Address;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("/account")
public class UpdateUserData {

	@RequestMapping("update")
	public String updateUserInformations(@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("addressId") Long addressId, @RequestParam("street") String street,
			@RequestParam("postalCode") String postalCode, @RequestParam("city") String city,
			@RequestParam("country") String country, Model model, HttpServletRequest request) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
		Address address = null;

		if (addressId != null)
			address = RepositoriesAccess.addressRepository.findById(addressId);
		if (address == null)
			address = new Address();

		address.setCity(city);
		address.setCountry(country);
		address.setPostalCode(postalCode);
		address.setStreet(street);
		user.setAddress(address);
		RepositoriesAccess.addressRepository.save(address);

		user.setName(name);
		user.setSurname(surname);
		// foundUser.setDateBirth(dateBirth);
		RepositoriesAccess.usersRepository.save(user);

		model.addAttribute("msg", "success");
		model.addAttribute("user", user);
		model.addAttribute("address", address);
		return "userAccount/options/changeData";
	}
	
	@RequestMapping("createAddress")
	public String createAddress(@RequestParam("street") String street, @RequestParam("postalCode") String postalCode,
			@RequestParam("city") String city, @RequestParam("country") String country, Model model) {
		Users user1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = RepositoriesAccess.usersRepository.findByLogin(user1.getLogin());
		Address address = new Address(street, postalCode, city, country);

		RepositoriesAccess.addressRepository.save(address);
		model.addAttribute("address", address);
		model.addAttribute("user", user);

		return "userAccount/options/changeData";
	}
}
