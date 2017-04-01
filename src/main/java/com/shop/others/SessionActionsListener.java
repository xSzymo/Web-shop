package com.shop.others;

import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.shop.data.tables.Books;

@Component
public class SessionActionsListener implements javax.servlet.http.HttpSessionListener, ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext instanceof WebApplicationContext)
			((WebApplicationContext) applicationContext).getServletContext().addListener(this);
		else
			throw new RuntimeException("Must be inside a web application context");

	}

	@Override
	public void sessionCreated(HttpSessionEvent session) {
		HashSet<Books> shopBasket = new HashSet<Books>();
		LinkedList<Books> shopBasketUno = new LinkedList<Books>();

		session.getSession().setAttribute("basket", shopBasket);
		session.getSession().setAttribute("basketWithAllBooks", shopBasketUno);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
}