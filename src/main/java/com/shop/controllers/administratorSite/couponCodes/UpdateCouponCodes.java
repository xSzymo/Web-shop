package com.shop.controllers.administratorSite.couponCodes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.data.tables.CouponCodes;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class UpdateCouponCodes {

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateSite(Model model) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/update";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateBook(@PathVariable Long id, Model model) {
		CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(id);

		if (couponCodes == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("couponCode", couponCodes);
		return "/administratorSite/couponCodesManager/updateOneCouponCode";
	}

	@RequestMapping(value = "update/updateOne", method = RequestMethod.POST)
	public String updateBook(@RequestParam("codeDiscount") String codeDiscount, @RequestParam("code") String code,
			@RequestParam("id") String id, Model model) {

		CouponCodes couponCode = RepositoriesAccess.couponCodesRepository.findById(Long.parseLong(id));

		if (couponCode == null) {
			model.addAttribute("msg", "not found couponCodes to update");
			return "administratorSite/couponCodesManager/updateOneCouponCode";
		}

		couponCode.setCode(code);
		couponCode.setCodeDiscount(Double.parseDouble(codeDiscount));

		RepositoriesAccess.couponCodesRepository.save(couponCode);
		model.addAttribute("couponCode", couponCode);
		model.addAttribute("msg", "Success");
		return "administratorSite/couponCodesManager/updateOneCouponCode";
	}
}
