package com.shop.configuration.system;

import com.shop.configuration.ApplicationProperties;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesInitializer {
    String propFileName = "application.properties";
    InputStream inputStream;
    private String URL = "";
    private String PROJECT_NAME = "";
    private String PICTURE_PATH = "";
    private String SHOP_EMAIL = "";
    private String SHOP_EMAIL_PASSWORD = "";
    private String DATABASE_NAME = "";
    private String DATABASE_USER_NAME = "";
    private String DATABASE_USER_PASSWORD = "";
    private String DATABASE_SERVER_NAME = "";
    private String DATABASE_PORT = "";

    public ApplicationPropertiesInitializer() {
        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            }

            URL = prop.getProperty("URL");
            PROJECT_NAME = prop.getProperty("PROJECT_NAME");
            PICTURE_PATH = prop.getProperty("PICTURE_PATH");
            SHOP_EMAIL = prop.getProperty("SHOP_EMAIL");
            SHOP_EMAIL_PASSWORD = prop.getProperty("SHOP_EMAIL_PASSWORD");
            DATABASE_NAME = prop.getProperty("DATABASE_NAME");
            DATABASE_USER_NAME = prop.getProperty("DATABASE_USER_NAME");
            DATABASE_USER_PASSWORD = prop.getProperty("DATABASE_USER_PASSWORD");
            DATABASE_SERVER_NAME = prop.getProperty("DATABASE_SERVER_NAME");
            DATABASE_PORT = prop.getProperty("DATABASE_PORT");

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ApplicationProperties.USE_APPLICATION_PROPERTIES_DATA) {
                if (!URL.equals(""))
                    ApplicationProperties.URL = URL;
                if (!PROJECT_NAME.equals(""))
                    ApplicationProperties.PROJECT_NAME = PROJECT_NAME;
                if (!PICTURE_PATH.equals("")) {
                    ApplicationProperties.PICTURE_PATH = PICTURE_PATH;
                    if (!ApplicationProperties.PICTURE_PATH.substring(ApplicationProperties.PICTURE_PATH.length() - 1).equals("/"))
                        ApplicationProperties.PICTURE_PATH += "/";
                }
                if (!SHOP_EMAIL.equals(""))
                    ApplicationProperties.SHOP_EMAIL = SHOP_EMAIL;
                if (!SHOP_EMAIL_PASSWORD.equals(""))
                    ApplicationProperties.SHOP_EMAIL_PASSWORD = SHOP_EMAIL_PASSWORD;
                if (!DATABASE_NAME.equals(""))
                    ApplicationProperties.DATABASE_NAME = DATABASE_NAME;
                if (!DATABASE_USER_NAME.equals(""))
                    ApplicationProperties.DATABASE_USER_NANE = DATABASE_USER_NAME;
                if (!DATABASE_USER_PASSWORD.equals(""))
                    ApplicationProperties.DATABASE_USER_PASSWORD = DATABASE_USER_PASSWORD;
            }
        }
    }

}
