package com.tpjad.ejbjpa.groceries.glassfish;

import com.tpjad.ejbjpa.groceries.utils.Constants;
import com.tpjad.ejbjpa.groceries.utils.HttpServletResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(Constants.LOGOUT_GF_SERVLET)
public class LogoutGFServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        session.removeAttribute(Constants.USERNAME);
        resp.sendRedirect(req.getContextPath() + Constants.LOGIN_GF_SERVLET);
        writer.close();
    }
}
