package com.tpjad.ejbjpa.groceries.utils;

public class LogoutHtml {
    public static final String LOGOUT_BUTTON = "<form method=\"post\" action=\"LogoutLocalServlet\">\n" +
            "    <input type=\"submit\" value=\"logout\">\n" +
            "</form>";

    public static final String USER_NOT_LOGGED = "<p style='color: red'>You are not logged</p>";
}
