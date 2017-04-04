package com.shop.controllers.administratorSite.couponCodes;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.others.RepositoriesAccess;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class DeleteCouponCodes {

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteSite(Model model) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/delete";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public RedirectView deleteFromButton(@PathVariable Long id, Model model, RedirectAttributes red) {
		CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(id);

		if (couponCodes == null)
			red.addFlashAttribute("msg", "not found");
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
			for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
				Orders order = iterator.next();
				if (order.getCouponCodes() != null)
					if (order.getCouponCodes().getId() == couponCodes.getId()) {
						order.setCouponCodes(null);
						RepositoriesAccess.ordersRepository.save(order);
					}
			}
			RepositoriesAccess.couponCodesRepository.delete(couponCodes);
			red.addFlashAttribute("msg", "Succes");
		}
		Iterable<CouponCodes> couponCodesAll = RepositoriesAccess.couponCodesRepository.findAll();
		red.addFlashAttribute("couponCodes", couponCodesAll);

		return new RedirectView(ApplicationConfig.PROJECT_NAME + "administratorSite/couponCodes/delete");
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteFromInputText(@RequestParam("id") Long id, Model model) {
		CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(id);

		if (couponCodes == null)
			model.addAttribute("msg", "not found");
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();
			for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
				Orders order = iterator.next();
				if (order.getCouponCodes() != null)
					if (order.getCouponCodes().getId() == couponCodes.getId()) {
						order.setCouponCodes(null);
						RepositoriesAccess.ordersRepository.save(order);
					}
			}

			RepositoriesAccess.couponCodesRepository.delete(couponCodes);
			model.addAttribute("msg", "Succes");
		}
		Iterable<CouponCodes> couponCodesAll = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodesAll);
		return "/administratorSite/couponCodesManager/delete";
	}
}
