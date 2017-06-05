package com.shop.others;

import com.shop.configuration.ApplicationConfig;
import com.shop.data.tables.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSessionEvent;
import java.util.HashSet;
import java.util.LinkedList;

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
        HashSet<Book> shopBasket = new HashSet<Book>();
        LinkedList<Book> shopBasketUno = new LinkedList<Book>();

        session.getSession().setAttribute("basket", shopBasket);
        session.getSession().setAttribute("basketWithAllBooks", shopBasketUno);
        session.getSession().setAttribute("PROJECT_NAME", ApplicationConfig.PROJECT_NAME);
        session.getSession().setAttribute("URL", ApplicationConfig.URL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}