package com.tpjad.ejbjpa.groceries.utils;

import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;

import java.util.List;

public class GroceriesHtml {
    public static String getWelcomeMessage(String user) {
        return "Welcome " + user + "! \n Here is your groceries list: ";
    }

    public static final String ADD_GROCERY_FORM = "<form method=\"post\" action=\"./GroceryAddLocalServlet\">\n" +
            " Description:<input type=\"text\" name=\"description\">\n" +
            " Nr Items:<input type=\"text\" name=\"nrItems\">\n" +
            " Till date:<input type=\"text\" name=\"tillDate\">\n" +
            " <input type=\"submit\" value=\"Add\" + /> " +
            "</form>";

    public static final String INVALID_GROCERY_FIELDS = "<p style='color: red'>Invalid fields!</p>";

    public static String createGroceriesTable(final List<GroceryDTO> groceries) {
        StringBuilder string = new StringBuilder();
        string.append("<table border=1>\n");
        string.append("<tr><td>Description</td><td>Nr Items</td><td>Till date</td><td>Delete</td></tr>\n");
        for (GroceryDTO g : groceries) {
            string.append("<tr><td>" + g.getDescription() + "</td><td> " + g.getNrItems() + "</td><td> " + g.getTillDate() + "</td><td> " + getDeleteForGrocery(g) + "</td></tr>\n");
        }
        string.append("</table>\n");
        return string.toString();
    }

    private static String getDeleteForGrocery(GroceryDTO grocery) {
        return "<a href='./GroceryDeleteLocalServlet?groceryId=" + grocery.getId() + "'>Delete</a>";
    }
}
