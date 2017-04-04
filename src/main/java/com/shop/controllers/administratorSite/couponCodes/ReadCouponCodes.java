package com.shop.controllers.administratorSite.couponCodes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.CouponCodes;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class ReadCouponCodes {

	@RequestMapping(value = "read", method = RequestMethod.GET)
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/read";
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	public String readOne(@RequestParam("id") String id, Model model) {
		CouponCodes couponCode = RepositoriesAccess.couponCodesRepository.findById(Long.parseLong(id));

		if (couponCode == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/couponCodesManager/read";
		}
		model.addAttribute("couponCode", couponCode);
		return "administratorSite/couponCodesManager/read";
	}
}
