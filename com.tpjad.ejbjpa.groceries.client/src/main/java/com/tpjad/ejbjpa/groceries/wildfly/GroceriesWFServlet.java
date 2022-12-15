package com.tpjad.ejbjpa.groceries.wildfly;

import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;
import com.tpjad.ejbjpa.groceries.interfaces.IGroceryServiceR;
import com.tpjad.ejbjpa.groceries.utils.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@WebServlet(Constants.GROCERIES_WF_SERVLET)
public class GroceriesWFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        String username = req.getSession().getAttribute(Constants.USERNAME).toString();
        if (ServiceUtils.isStringNullOrEmpty(username)) {
            resp.sendRedirect(req.getContextPath() + Constants.LOGIN_WF_SERVLET);
        }
        PrintWriter writer = resp.getWriter();
        writer.println(LogoutHtml.LOGOUT_BUTTON_WF);
        writer.println(GroceriesHtml.getWelcomeMessage(username));
        try {
            IGroceryServiceR groceryService = ContextLookupUtils.lookupGroceryServiceJNDIWF();
            log.info("Getting groceries for user: {}", username);
            List<GroceryDTO> all = groceryService.findAll(username);
            writer.println(GroceriesHtml.createGroceriesTableWF(all));
            writer.println(GroceriesHtml.ADD_GROCERY_FORM_WF);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        writer.close();
    }


}
