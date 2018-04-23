package com.shop.controllers.administratorSite.couponCodes;

import com.shop.configuration.ApplicationProperties;
import com.shop.data.services.CouponCodesService;
import com.shop.data.tables.CouponCode;
import com.shop.data.tables.Order;
import com.shop.others.RepositoriesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Iterator;

@Controller
@RequestMapping("administratorSite/couponCodes")
public class DeleteCouponCodes {
    @Autowired
    private CouponCodesService couponCodesService;

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSite(Model model) {
        Iterable<CouponCode> couponCodes = couponCodesService.findAll();

        model.addAttribute("couponCodes", couponCodes);
        return "administratorSite/couponCodesManager/delete";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public RedirectView deleteFromButton(@PathVariable Long id, Model model, RedirectAttributes red) {
        CouponCode couponCodes = couponCodesService.findOne(id);

        if (couponCodes == null)
            red.addFlashAttribute("msg", "not found");
        else {
            couponCodesService.delete(couponCodes);
            red.addFlashAttribute("msg", "Succes");
        }
        Iterable<CouponCode> couponCodesAll = couponCodesService.findAll();
        red.addFlashAttribute("couponCodes", couponCodesAll);

        return new RedirectView(ApplicationProperties.PROJECT_NAME + "administratorSite/couponCodes/delete");
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteFromInputText(@RequestParam("id") Long id, Model model) {
        CouponCode couponCodes = couponCodesService.findOne(id);

        if (couponCodes == null)
            model.addAttribute("msg", "not found");
        else {
            couponCodesService.delete(couponCodes);
            model.addAttribute("msg", "Succes");
        }
        Iterable<CouponCode> couponCodesAll = couponCodesService.findAll();

        model.addAttribute("couponCodes", couponCodesAll);
        return "/administratorSite/couponCodesManager/delete";
    }
}
