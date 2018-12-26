package edu.core.java.rabbitbag.shared;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.core.java.rabbitbag.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private Properties properties;
    private Bool isDevelopment;

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
        return true ? properties.getProperty("databaseType.dev") : properties.getProperty("databaseType.prd");
    }

}
