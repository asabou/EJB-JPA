package com.tpjad.ejbjpa.groceries.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties getProperties() {
        Properties properties = new Properties();

        try {
            ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
            InputStream applicationPropertiesStream = classLoader.getResourceAsStream("application.properties");
            properties.load(applicationPropertiesStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
        }
        return properties;
    }
}
