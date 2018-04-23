package com.shop.controllers.administratorSite.couponCodes;

import com.shop.data.services.CouponCodesService;
import com.shop.data.tables.CouponCode;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class CreateCouponCodes {
    @Autowired
    private CouponCodesService couponCodesService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createSite() {
        return "administratorSite/couponCodesManager/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("codeDiscount") String codeDiscount, @RequestParam("code") String code,
                         Model model) {

        CouponCode couponCodeFound = couponCodesService.findOneByCode(code);
        Iterable<CouponCode> couponCodes = couponCodesService.findAll();

        if (couponCodeFound != null) {
            model.addAttribute("msgError", "couponCode already exist");
            model.addAttribute("couponCodes", couponCodes);
            return "administratorSite/couponCodesManager/create";
        }
        CouponCode couponCode = new CouponCode(Double.parseDouble(codeDiscount), code);

        couponCodesService.save(couponCode);
        model.addAttribute("msgSuccess", "success");

        return "administratorSite/couponCodesManager/create";
    }
}
