package com.shop.controllers.administratorSite.couponCodes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class CouponCodesSite {
	
	@RequestMapping
	public String start() {
		return "administratorSite/couponCodesStartPage";
	}
}
