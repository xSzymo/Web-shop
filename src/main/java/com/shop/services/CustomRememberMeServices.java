package com.shop.services;

import com.shop.data.operations.CookiesDAO;
import com.shop.data.operations.UserDAO;
import com.shop.data.tables.Cookies;
import com.shop.data.tables.User;
import com.shop.others.Coder;
import com.shop.others.RepositoriesAccess;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("rememberMeService")
public class CustomRememberMeServices implements RememberMeServices {

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        if (!CookiesDAO.checkCookiesExistsAndDeleteIfNo(request, response))
            return null;
        if (CookiesDAO.isCookieStillActualDeleteIfNo(request, response))
            return null;

        Cookies a = null;
        Cookies a1 = null;
        User user = null;

        if (request.getCookies().length < 2)
            return null;

        for (Cookie x : request.getCookies())
            if (x.getName().equals("remember")) {
                a = RepositoriesAccess.cookiesRepository.findByValue(x.getValue());
                a1 = RepositoriesAccess.cookiesRepository.findByName(x.getValue());
                user = CookiesDAO.findConnectUserWithCookie(a1);
            }

        if (a == null || a1 == null || user == null)
            return null;

        if (user.getCookieCode().equals(a1.getValue()))
            if (a1.getName().equals(a.getValue()))
                return UserDAO.login(user.getLogin(), user.getPassword());
        return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
                             Authentication successfulAuthentication) {

        User user = RepositoriesAccess.usersRepository
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (request.getCookies().length > 1)
            CookiesDAO.deleteCurrentCookies(request, response);

        String codeCookieWithCookie = Coder.getUniqueCode();
        String codeCookieWithUser = Coder.getUniqueCode();

        Cookies mainCookie = new Cookies(codeCookieWithCookie, codeCookieWithUser);
        Cookies rememberMeCookie = new Cookies("remember", codeCookieWithCookie);
        user.setCookieCode(codeCookieWithUser);

        Cookie c = new Cookie(mainCookie.getName(), mainCookie.getValue());
        c.setMaxAge(604800);
        response.addCookie(c);
        Cookie c1 = new Cookie("remember", rememberMeCookie.getValue());
        c1.setMaxAge(604800);
        response.addCookie(c1);

        mainCookie.setDate(CookiesDAO.getEndCookieDate(7));
        rememberMeCookie.setDate(CookiesDAO.getEndCookieDate(7));

        RepositoriesAccess.cookiesRepository.save(mainCookie);
        RepositoriesAccess.cookiesRepository.save(rememberMeCookie);
        RepositoriesAccess.usersRepository.save(user);
    }
}
