package edu.core.java.rabbitbag.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private Properties properties;
    private boolean isDevelopment = System.getProperty("envType").equals("dev");

    private ApplicationProperties() {
        properties = new Properties();

        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(in);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static class Holder {
        private static final ApplicationProperties INSTANCE = new ApplicationProperties();
    }

    public static ApplicationProperties shared() {
        return Holder.INSTANCE;
    }

    public String getDatabaseType() {
        // is development
        return isDevelopment ? properties.getProperty("databaseType.dev") : properties.getProperty("databaseType.prd");
    }

    public boolean isDevelopment() {
        return isDevelopment;
    }

}
