package com.shop.controllers.usersAccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.shop.data.tables.Orders;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
@Controller
@RequestMapping("/account")
public class usersAccount {

	@RequestMapping
	public String halo1() {
		return "userAccount/userAccount";
	}

}
