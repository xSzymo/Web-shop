package com.shop.data.operations;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.data.tables.Cookies;
import com.shop.data.tables.User;
import com.shop.others.RepositoriesAccess;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CookiesDAO {
	public static boolean isCookieStillActualDeleteIfNo(HttpServletRequest request, HttpServletResponse response) {
		Cookies a = null;
		Cookies a1 = null;

		for (Cookie x : request.getCookies())
			if (x.getName().equals("remember"))
				a = RepositoriesAccess.cookiesRepository.findByValue(x.getValue());

		if (a != null)
			for (Cookie x : request.getCookies())
				if (x.getName().equals(a.getValue()))
					a1 = RepositoriesAccess.cookiesRepository.findByName(x.getName());

		Date date = new Date();
		LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Date cookieEndDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

		if (a.getDate().before(cookieEndDate) || a1.getDate().before(cookieEndDate)) {
			deleteCurrentCookies(request, response);
			return true;
		}
		return false;
	}

	public static Date getEndCookieDate(int days) {
		Date date = new Date();
		LocalDateTime localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDate = localDate.plusDays(days);
		Date cookieEndDate = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		return cookieEndDate;
	}

	public static Long findConnectCookies(Cookies cookie) {
		Iterable<Cookies> cookies = RepositoriesAccess.cookiesRepository.findAll();
		Cookies foundCookie = null;
		for (Cookies x : cookies)
			if (x.getName().equals(cookie.getValue()))
				foundCookie = x;

		return (foundCookie.getId() != null ? foundCookie.getId() : 0);
	}

	public static User findConnectUserWithCookie(Cookies cookie) {
		Iterable<User> users = RepositoriesAccess.usersRepository.findAll();
		for (User x : users)
			if (x.getCookieCode().equals(cookie.getValue()))
				return RepositoriesAccess.usersRepository.findById(x.getId());

		return null;
	}

	public static boolean checkCookiesExistsAndDeleteIfNo(HttpServletRequest request, HttpServletResponse response) {
		Cookies a = null;
		Cookies a1 = null;
		User user = null;

		try {
			for (Cookie x : request.getCookies())
				if (x.getName().equals("remember"))
					a = RepositoriesAccess.cookiesRepository.findByValue(x.getValue());
		} catch(NullPointerException e) {
			return false;
		}
		if (a != null)
			for (Cookie x : request.getCookies())
				if (x.getName().equals(a.getValue()))
					a1 = RepositoriesAccess.cookiesRepository.findByName(x.getName());

		if (a != null && a1 != null)
			user = CookiesDAO.findConnectUserWithCookie(a1);

		if (a == null || a1 == null || user == null) {
			deleteCookiesIfDoeasntExists(request, response);
			return false;
		}

		return true;
	}

	public static void deleteCookiesIfDoeasntExists(HttpServletRequest request, HttpServletResponse response) {
		for (Cookie x : request.getCookies()) {
			if (x.getName().equals("JSESSIONID"))
				continue;
			Cookie cookie = new Cookie(x.getName(), x.getValue());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	public static void deleteCurrentCookies(HttpServletRequest request, HttpServletResponse response) {
		if (!checkCookiesExistsAndDeleteIfNo(request, response))
			return;

		Cookies a = null;
		Cookies a1 = null;
		User user = null;

		for (Cookie x : request.getCookies())
			if (x.getName().equals("remember")) {
				a = RepositoriesAccess.cookiesRepository.findByValue(x.getValue());
				a1 = RepositoriesAccess.cookiesRepository.findByName(x.getValue());
				user = CookiesDAO.findConnectUserWithCookie(a1);
			}

		if (a == null || a1 == null || user == null)
			return;

		user.setCookieCode(null);

		Cookie x = new Cookie(a.getName(), a.getValue());
		x.setMaxAge(0);
		response.addCookie(x);
		Cookie x1 = new Cookie(a1.getName(), a1.getValue());
		x1.setMaxAge(0);
		response.addCookie(x1);

		RepositoriesAccess.cookiesRepository.delete(a.getId());
		RepositoriesAccess.cookiesRepository.delete(a1.getId());
		RepositoriesAccess.usersRepository.save(user);
	}
}
