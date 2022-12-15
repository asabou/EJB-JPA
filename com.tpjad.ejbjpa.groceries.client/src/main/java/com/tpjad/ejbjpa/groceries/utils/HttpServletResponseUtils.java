package com.tpjad.ejbjpa.groceries.utils;

import jakarta.servlet.http.HttpServletResponse;

public class HttpServletResponseUtils {
    public static void initResponse(HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Origin",  "*");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    }
}
