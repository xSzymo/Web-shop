package com.shop.controllers.administratorSite.couponCodes;

import com.shop.data.services.CouponCodesService;
import com.shop.data.tables.CouponCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class ReadCouponCodes {
    @Autowired
    private CouponCodesService couponCodesService;

    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readSite(Model model, HttpServletRequest request) {
        Iterable<CouponCode> couponCodes = couponCodesService.findAll();

        model.addAttribute("couponCodes", couponCodes);
        return "administratorSite/couponCodesManager/read";
    }

    @RequestMapping(value = "read", method = RequestMethod.POST)
    public String readOne(@RequestParam("id") String id, Model model) {
        CouponCode couponCode = couponCodesService.findOne(Long.parseLong(id));

        if (couponCode == null) {
            model.addAttribute("msg", "not found");
            return "administratorSite/couponCodesManager/read";
        }
        model.addAttribute("couponCode", couponCode);
        return "administratorSite/couponCodesManager/read";
    }
}
