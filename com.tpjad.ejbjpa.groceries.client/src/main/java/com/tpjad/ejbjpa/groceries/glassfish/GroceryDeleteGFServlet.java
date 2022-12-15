package com.tpjad.ejbjpa.groceries.glassfish;

import com.tpjad.ejbjpa.groceries.interfaces.IGroceryServiceR;
import com.tpjad.ejbjpa.groceries.utils.Constants;
import com.tpjad.ejbjpa.groceries.utils.ContextLookupUtils;
import com.tpjad.ejbjpa.groceries.utils.ServiceUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.naming.NamingException;
import java.io.IOException;

@Slf4j
@WebServlet(Constants.GROCERY_DELETE_GF_SERVLET)
public class GroceryDeleteGFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groceryId = req.getParameter("groceryId");
        log.info("Trying to delete grocery by id {}", groceryId);
        try {
            IGroceryServiceR groceryService = ContextLookupUtils.lookupGroceryServiceJNDIGF();
            if (!ServiceUtils.isStringNullOrEmpty(groceryId)) {
                groceryService.delete(ServiceUtils.convertStringToLong(groceryId));
            }
            resp.sendRedirect(req.getContextPath() + Constants.GROCERIES_GF_SERVLET);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
