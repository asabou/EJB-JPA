package com.tpjad.ejbjpa.groceries.client;

import com.tpjad.ejbjpa.groceries.interfaces.IGroceryService;
import com.tpjad.ejbjpa.groceries.interfaces.ILoginService;
import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;
import com.tpjad.ejbjpa.groceries.utils.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@WebServlet(Constants.GROCERIES_LOCAL_SERVLET)
public class GroceriesLocalServlet extends HttpServlet {
    @EJB
    private ILoginService loginService;

    @EJB
    private IGroceryService groceryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        log.info("Groceries servlet: {}", loginService.isLoggedIn());
        String username = req.getSession().getAttribute(Constants.USERNAME).toString();
        if (ServiceUtils.isStringNullOrEmpty(username)) {
            resp.sendRedirect(req.getContextPath() + Constants.LOGIN_LOCAL_SERVLET);
        }
        PrintWriter writer = resp.getWriter();
        writer.println(LogoutHtml.LOGOUT_BUTTON);
        writer.println(GroceriesHtml.getWelcomeMessage(username));
        List<GroceryDTO> all = groceryService.findAll(username);
        writer.println(GroceriesHtml.createGroceriesTable(all));
        writer.println(GroceriesHtml.ADD_GROCERY_FORM);
        writer.close();
    }
}
