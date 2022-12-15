package com.tpjad.ejbjpa.groceries.glassfish;

import com.tpjad.ejbjpa.groceries.interfaces.ILoginServiceR;
import com.tpjad.ejbjpa.groceries.interfaces.UserDTO;
import com.tpjad.ejbjpa.groceries.utils.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(Constants.LOGIN_GF_SERVLET)
public class LoginGFServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        PrintWriter writer = resp.getWriter();
        writer.println(LoginHtml.LOGIN_OR_CREATE_ACCOUNT_FORM_GF);
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        PrintWriter writer = resp.getWriter();
        String login = req.getParameter(Constants.LOGIN);
        String create = req.getParameter(Constants.CREATE);
        String username = req.getParameter(Constants.USERNAME);
        String password = req.getParameter(Constants.PASSWORD);
        if (ServiceUtils.isStringNullOrEmpty(username) || ServiceUtils.isStringNullOrEmpty(password)) {
            writer.println(LoginHtml.INVALID_FIELDS + " Username and password must be filled!");
            writer.println(LoginHtml.REDIRECT_TO_LOGIN_GF);
        } else {
            try {
                ILoginServiceR loginService = ContextLookupUtils.lookupLoginServiceJNDIGF();
                if (!ServiceUtils.isStringNullOrEmpty(login)) {
                    loginService.login(username, password);
                    if (loginService.isLoggedIn()) {
                        HttpSession session = req.getSession();
                        session.setAttribute(Constants.USERNAME, loginService.getLoggedUser().getUsername());
                        resp.sendRedirect(req.getContextPath() + Constants.GROCERIES_GF_SERVLET);
                    } else {
                        writer.println(LoginHtml.LOGIN_FAILED);
                        writer.println(LoginHtml.REDIRECT_TO_LOGIN_GF);
                    }
                } else if (!ServiceUtils.isStringNullOrEmpty(create)) {
                    if (!ServiceUtils.isObjectNull(loginService.findByUsername(username))) {
                        writer.println(LoginHtml.USER_ALREADY_EXISTS);
                        writer.println(LoginHtml.REDIRECT_TO_LOGIN_GF);
                    } else {
                        loginService.create(new UserDTO(username, password));
                        writer.println(LoginHtml.ACCOUNT_CREATED);
                        writer.println(LoginHtml.LOGIN_WITH_NEW_USER_GF);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writer.close();
    }
}
