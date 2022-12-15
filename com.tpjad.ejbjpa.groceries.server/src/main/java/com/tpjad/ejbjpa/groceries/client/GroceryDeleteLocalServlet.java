package com.tpjad.ejbjpa.groceries.client;

import com.tpjad.ejbjpa.groceries.interfaces.IGroceryService;
import com.tpjad.ejbjpa.groceries.utils.Constants;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(Constants.GROCERY_DELETE_LOCAL_SERVLET)
public class GroceryDeleteLocalServlet extends HttpServlet {
    @EJB
    private IGroceryService groceryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groceryId = req.getParameter("groceryId");
        log.info("Trying to delete grocery by id {}", groceryId);
        if (!ServiceUtils.isStringNullOrEmpty(groceryId)) {
            groceryService.delete(ServiceUtils.convertStringToLong(groceryId));
        }
        resp.sendRedirect(req.getContextPath() + Constants.GROCERIES_LOCAL_SERVLET);
    }
}
