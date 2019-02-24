package com.shop.others;

import com.shop.configuration.ApplicationProperties;
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
        if (ApplicationProperties.FALSE_WHILE_RUNNING_DB_TESTS) {
            if (applicationContext instanceof WebApplicationContext) {
                try {
                    ((WebApplicationContext) applicationContext).getServletContext().addListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException("Must be inside a web application context");
            }
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent session) {
        if (ApplicationProperties.FALSE_WHILE_RUNNING_DB_TESTS) {
            HashSet<Book> shopBasket = new HashSet<Book>();
            LinkedList<Book> shopBasketUno = new LinkedList<Book>();

            session.getSession().setAttribute("basket", shopBasket);
            session.getSession().setAttribute("basketWithAllBooks", shopBasketUno);
            session.getSession().setAttribute("PROJECT_NAME", ApplicationProperties.PROJECT_NAME);
            session.getSession().setAttribute("URL", ApplicationProperties.URL);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}