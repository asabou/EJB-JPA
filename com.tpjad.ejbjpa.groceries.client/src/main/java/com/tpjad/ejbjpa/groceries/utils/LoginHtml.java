package com.tpjad.ejbjpa.groceries.utils;

public interface LoginHtml {
    String LOGIN_OR_CREATE_ACCOUNT_FORM_WF = "<form method='post' action='./LoginWFServlet'>"
            + "Username: <input type='text' name='username'><br>"
            + "Password: <input type='password' name='password'><br>"
            + "<input type='submit' name='login' value='Login'><input type='submit' name='create' value='Create'>"
            + "</form";

    String LOGIN_OR_CREATE_ACCOUNT_FORM_GF = "<form method='post' action='./LoginGFServlet'>"
            + "Username: <input type='text' name='username'><br>"
            + "Password: <input type='password' name='password'><br>"
            + "<input type='submit' name='login' value='Login'><input type='submit' name='create' value='Create'>"
            + "</form";

    String LOGIN_FAILED = "<p style='color: red'>Login failed! Wrong username and password!</p>";

    String INVALID_FIELDS = "<p style='color: red'>Invalid fields! Fields must be not empty!</p>";

    String REDIRECT_TO_LOGIN_WF = "<a href='./LoginWFServlet'>Try again!<a>";

    String REDIRECT_TO_LOGIN_GF = "<a href='./LoginGFServlet'>Try again!<a>";

    String USER_ALREADY_EXISTS = "<p style='color: red'>User already exists!</p>";

    String ACCOUNT_CREATED = "<p style='color: blue'> Account created </p>";

    String LOGIN_WITH_NEW_USER_GF = "<a href='./LoginGFServlet'>Login with the new user! </a>";

    String LOGIN_WITH_NEW_USER_WF = "<a href='./LoginWFServlet'>Login with the new user! </a>";
}
