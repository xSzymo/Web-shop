package com.shop.configuration;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesInitializer {
    public String URL = "";
    public String PROJECT_NAME = "";
    public String PICTURE_PATH = "";
    public String SHOP_EMAIL = "";
    public String SHOP_EMAIL_PASSWORD = "";
    public String DATABASE_NAME = "";
    public String DATABASE_USER_NANE = "";
    public String DATABASE_USER_PASSWORD = "";
    public String DATABASE_SERVER_NAME = "";
    public String DATABASE_PORT = "";

    String propFileName = "application.properties";
    InputStream inputStream;

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
            DATABASE_USER_NANE = prop.getProperty("DATABASE_USER_NANE");
            DATABASE_USER_PASSWORD = prop.getProperty("DATABASE_USER_PASSWORD");
            DATABASE_SERVER_NAME = prop.getProperty("SERVER_NAME");
            DATABASE_PORT = prop.getProperty("PORT");

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ApplicationConfig.USE_APPLICATION_PROPERTIES_DATA) {
                if (!URL.equals(""))
                    ApplicationConfig.URL = URL;
                if (!PROJECT_NAME.equals(""))
                    ApplicationConfig.PROJECT_NAME = PROJECT_NAME;
                if (!PICTURE_PATH.equals(""))
                    ApplicationConfig.PICTURE_PATH = PICTURE_PATH;
                if (!SHOP_EMAIL.equals(""))
                    ApplicationConfig.SHOP_EMAIL = SHOP_EMAIL;
                if (!SHOP_EMAIL_PASSWORD.equals(""))
                    ApplicationConfig.SHOP_EMAIL_PASSWORD = SHOP_EMAIL_PASSWORD;
                if (!DATABASE_NAME.equals(""))
                    ApplicationConfig.DATABASE_NAME = DATABASE_NAME;
                if (!DATABASE_USER_NANE.equals(""))
                    ApplicationConfig.DATABASE_USER_NANE = DATABASE_USER_NANE;
                if (!DATABASE_USER_PASSWORD.equals(""))
                    ApplicationConfig.DATABASE_USER_PASSWORD = DATABASE_USER_PASSWORD;
            }
        }
    }

}
