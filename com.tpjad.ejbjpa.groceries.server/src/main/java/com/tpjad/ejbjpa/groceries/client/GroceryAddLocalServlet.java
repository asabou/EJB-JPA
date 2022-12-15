package com.tpjad.ejbjpa.groceries.client;

import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;
import com.tpjad.ejbjpa.groceries.interfaces.IGroceryService;
import com.tpjad.ejbjpa.groceries.utils.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@WebServlet(Constants.GROCERY_ADD_LOCAL_SERVLET)
public class GroceryAddLocalServlet extends HttpServlet {
    @EJB
    private IGroceryService groceryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletResponseUtils.initResponse(resp);
        PrintWriter writer = resp.getWriter();
        writer.println(LogoutHtml.LOGOUT_BUTTON);
        HttpSession session = req.getSession();
        String username = session.getAttribute(Constants.USERNAME).toString();
        String description = req.getParameter("description");
        String nrItem = req.getParameter("nrItems");
        String tillDate = req.getParameter("tillDate");
        log.info("user: {}", username);
        log.info("description: {}", description);
        log.info("nrItems: {}", nrItem);
        if (
                ServiceUtils.isStringNullOrEmpty(description) ||
                        ServiceUtils.isStringNullOrEmpty(nrItem) ||
                        ServiceUtils.isStringNullOrEmpty(tillDate)
        ) {
            writer.println(LogoutHtml.LOGOUT_BUTTON);
            writer.println(GroceriesHtml.INVALID_GROCERY_FIELDS);
            writer.println(GroceriesHtml.getWelcomeMessage(username));
            List<GroceryDTO> all = groceryService.findAll(username);
            writer.println(GroceriesHtml.createGroceriesTable(all));
            writer.println(GroceriesHtml.ADD_GROCERY_FORM);
        } else {
            groceryService.create(new GroceryDTO(username, description, nrItem, tillDate));
            resp.sendRedirect(req.getContextPath() + Constants.GROCERIES_LOCAL_SERVLET);
        }
        writer.close();
    }
}
