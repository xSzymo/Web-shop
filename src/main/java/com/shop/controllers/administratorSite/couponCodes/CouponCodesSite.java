package com.shop.controllers.administratorSite.couponCodes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class CouponCodesSite {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String start() {
		return "administratorSite/couponCodesStartPage";
	}
}
