package com.tpjad.ejbjpa.groceries.utils;

import com.tpjad.ejbjpa.groceries.interfaces.GroceryDTO;

import java.util.List;

public class GroceriesHtml {
    public static String getWelcomeMessage(String user) {
        return "Welcome " + user + "! \n Here is your groceries list: ";
    }

    public static final String ADD_GROCERY_FORM_LOCAL = "<form method=\"post\" action=\"./GroceryAddLocalServlet\">\n" +
            " Description:<input type=\"text\" name=\"description\">\n" +
            " Nr Items:<input type=\"text\" name=\"nrItems\">\n" +
            " Till date:<input type=\"text\" name=\"tillDate\">\n" +
            " <input type=\"submit\" value=\"Add\" + /> " +
            "</form>";

    public static final String ADD_GROCERY_FORM_WF = "<form method=\"post\" action=\"./GroceryAddWFServlet\">\n" +
            " Description:<input type=\"text\" name=\"description\">\n" +
            " Nr Items:<input type=\"text\" name=\"nrItems\">\n" +
            " Till date:<input type=\"text\" name=\"tillDate\">\n" +
            " <input type=\"submit\" value=\"Add\" + /> " +
            "</form>";

    public static final String ADD_GROCERY_FORM_GF = "<form method=\"post\" action=\"./GroceryAddGFServlet\">\n" +
            " Description:<input type=\"text\" name=\"description\">\n" +
            " Nr Items:<input type=\"text\" name=\"nrItems\">\n" +
            " Till date:<input type=\"text\" name=\"tillDate\">\n" +
            " <input type=\"submit\" value=\"Add\" + /> " +
            "</form>";

    public static final String INVALID_GROCERY_FIELDS = "<p style='color: red'>Invalid fields!</p>";

    public static String createGroceriesTableLocal(final List<GroceryDTO> groceries) {
        StringBuilder string = new StringBuilder();
        string.append("<table border=1>\n");
        string.append("<tr><td>Description</td><td>Nr Items</td><td>Till date</td><td>Delete</td></tr>\n");
        for (GroceryDTO g : groceries) {
            string.append("<tr><td>" + g.getDescription() + "</td><td> " + g.getNrItems() + "</td><td> " + g.getTillDate() + "</td><td> " + getDeleteForGroceryLocal(g) + "</td></tr>\n");
        }
        string.append("</table>\n");
        return string.toString();
    }

    public static String createGroceriesTableGF(final List<GroceryDTO> groceries) {
        StringBuilder string = new StringBuilder();
        string.append("<table border=1>\n");
        string.append("<tr><td>Description</td><td>Nr Items</td><td>Till date</td><td>Delete</td></tr>\n");
        for (GroceryDTO g : groceries) {
            string.append("<tr><td>" + g.getDescription() + "</td><td> " + g.getNrItems() + "</td><td> " + g.getTillDate() + "</td><td> " + getDeleteForGroceryGF(g) + "</td></tr>\n");
        }
        string.append("</table>\n");
        return string.toString();
    }

    public static String createGroceriesTableWF(final List<GroceryDTO> groceries) {
        StringBuilder string = new StringBuilder();
        string.append("<table border=1>\n");
        string.append("<tr><td>Description</td><td>Nr Items</td><td>Till date</td><td>Delete</td></tr>\n");
        for (GroceryDTO g : groceries) {
            string.append("<tr><td>" + g.getDescription() + "</td><td> " + g.getNrItems() + "</td><td> " + g.getTillDate() + "</td><td> " + getDeleteForGroceryWF(g) + "</td></tr>\n");
        }
        string.append("</table>\n");
        return string.toString();
    }

    private static String getDeleteForGroceryLocal(GroceryDTO grocery) {
        return "<a href='./GroceryDeleteLocalServlet?groceryId=" + grocery.getId() + "'>Delete</a>";
    }

    private static String getDeleteForGroceryWF(GroceryDTO grocery) {
        return "<a href='./GroceryDeleteWFServlet?groceryId=" + grocery.getId() + "'>Delete</a>";
    }

    private static String getDeleteForGroceryGF(GroceryDTO grocery) {
        return "<a href='./GroceryDeleteGFServlet?groceryId=" + grocery.getId() + "'>Delete</a>";
    }
}
