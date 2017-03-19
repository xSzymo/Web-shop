package com.shop.controllers.administratorSite.couponCodes;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Books;
import com.shop.data.tables.Categories;
import com.shop.data.tables.CouponCodes;
import com.shop.data.tables.Orders;
import com.shop.data.tables.Pictures;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;


@Controller
@RequestMapping("administratorSite/couponCodes")
public class couponCodesCRUD {
	
	@RequestMapping
	public String createSit12e() {
		return "administratorSite/couponCodesStartPage";
	}

	@RequestMapping("create")
	public String createSite() {
		return "administratorSite/couponCodesManager/create";
	}

	@RequestMapping("/createCouponCode")
	public String create1(@RequestParam("codeDiscount") String codeDiscount, @RequestParam("code") String code, Model model) {

		CouponCodes couponCodeFound = RepositoriesAccess.couponCodesRepository.findByCode(code);

		if (couponCodeFound != null) {
			model.addAttribute("msgError", "couponCode already exist");
			return "administratorSite/couponCodesManager/create";
		}
		CouponCodes couponCode = new CouponCodes(Double.parseDouble(codeDiscount), code);
		 
		RepositoriesAccess.couponCodesRepository.save(couponCode);
		model.addAttribute("msgSuccess", "success");

		return "administratorSite/couponCodesManager/create";
	}

	@RequestMapping("read")
	public String readSite(Model model, HttpServletRequest request) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/read";
	}

	@RequestMapping("readOne")
	public String readOne(@RequestParam("id") String id, Model model) {
		 CouponCodes couponCode = RepositoriesAccess.couponCodesRepository.findById(Long.parseLong(id));
		 
		if (couponCode == null) {
			model.addAttribute("msg", "not found");
			return "administratorSite/couponCodesManager/read";
		}
		model.addAttribute("couponCode", couponCode);
		return "administratorSite/couponCodesManager/read";
	}

	@RequestMapping("/update")
	public String updateEmpl(Model model) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();
		
		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/update";
	}

	@RequestMapping("updateCouponCodes/update")
	public String updateBook(@RequestParam("codeDiscount") String codeDiscount, @RequestParam("code") String code, @RequestParam("id") String id, Model model) {

		 CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(Long.parseLong(id));
		
		if (couponCodes == null) {
			model.addAttribute("msg", "not found couponCodes to update");
			return "administratorSite/couponCodesManager/updateOneCategory";
		}

		couponCodes.setCode(code);
		couponCodes.setCodeDiscount(Double.parseDouble(codeDiscount));
		
		RepositoriesAccess.couponCodesRepository.save(couponCodes);
		model.addAttribute("couponCodes", couponCodes);
		model.addAttribute("msg", "Success");
		return "administratorSite/couponCodesManager/updateOneCategory";
	}

	@RequestMapping(value = "updateCouponCodes/{couponCodesId}")
	public String updateBook(@PathVariable Long couponCodesId, Model model) {
		 CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(couponCodesId);

		if (couponCodes == null)
			model.addAttribute("msg", "not found");

		model.addAttribute("couponCode", couponCodes);
		return "/administratorSite/couponCodesManager/updateOneCategory";
	}

	
	@RequestMapping("delete")
	public String deleteSite(Model model) {
		Iterable<CouponCodes> couponCodes = RepositoriesAccess.couponCodesRepository.findAll();

		model.addAttribute("couponCodes", couponCodes);
		return "administratorSite/couponCodesManager/delete";
	}

	@RequestMapping(value = "deleteCouponCodes/{couponCodeId}")
	public RedirectView deleteB(@PathVariable Long couponCodeId, Model model) {
		 CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(couponCodeId);

		if (couponCodes == null)
			model.addAttribute("msg", "not found");// wont work for redirectView
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();			
			for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
				Orders order = iterator.next();
				if(order.getCouponCodes() != null)
					if (order.getCouponCodes().getId() == couponCodes.getId()) {
						order.setCouponCodes(null);
						RepositoriesAccess.ordersRepository.save(order);
				}
			}			
			
			RepositoriesAccess.couponCodesRepository.delete(couponCodes);
			model.addAttribute("msg", "Succes, back to delete more");// wont
																		// work
																		// for
																		// redirectView
		}
		Iterable<CouponCodes> couponCodesAll = RepositoriesAccess.couponCodesRepository.findAll();
		model.addAttribute("couponCodes", couponCodesAll);

		return new RedirectView(ApplicationConfig.projectName + "administratorSite/couponCodes/delete");
	}

	@RequestMapping(value = "deleteCouponCodes")
	public String deletbveB(@RequestParam("id") Long id, Model model) {
		 CouponCodes couponCodes = RepositoriesAccess.couponCodesRepository.findById(id);

		if (couponCodes == null)
			model.addAttribute("msg", "not found");
		else {
			Iterable<Orders> orders = RepositoriesAccess.ordersRepository.findAll();			
			for (Iterator<Orders> iterator = orders.iterator(); iterator.hasNext();) {
				Orders order = iterator.next();
				if(order.getCouponCodes() != null)
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
