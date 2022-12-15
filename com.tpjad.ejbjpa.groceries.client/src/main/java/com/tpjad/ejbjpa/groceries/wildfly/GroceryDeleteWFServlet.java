package com.tpjad.ejbjpa.groceries.wildfly;

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
@WebServlet(Constants.GROCERY_DELETE_WF_SERVLET)
public class GroceryDeleteWFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groceryId = req.getParameter("groceryId");
        log.info("Trying to delete grocery by id {}", groceryId);
        try {
            IGroceryServiceR groceryService = ContextLookupUtils.lookupGroceryServiceJNDIEJBWF();
            if (!ServiceUtils.isStringNullOrEmpty(groceryId)) {
                groceryService.delete(ServiceUtils.convertStringToLong(groceryId));
            }
            resp.sendRedirect(req.getContextPath() + Constants.GROCERIES_WF_SERVLET);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
