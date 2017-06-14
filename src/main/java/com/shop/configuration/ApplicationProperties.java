package com.shop.configuration;

public class ApplicationProperties {
    /**
     * URL
     */
    public static String URL = "http://localhost:8080";

    /**
     * Project name in URL
     * You have to change it here and in pom.xml
     */
    public static String PROJECT_NAME = "/WebShop/";

    /**
     * Picture path
     */
    public static String PICTURE_PATH = "D:/WebShop/";

    /**
     * Shop email address
     * Use gmail
     */
    public static String SHOP_EMAIL = "examplewebshop@gmail.com";

    /**
     * Shop email password
     */
    public static String SHOP_EMAIL_PASSWORD = "ZAQ!2wsx";
    /**
     * Access to Data base
     */
    public static String DATABASE_NAME = "crud";
    public static String DATABASE_USER_NANE = "root";
    public static String DATABASE_USER_PASSWORD = "admin";
    public static String SERVER_NAME = "localhost";
    public static String PORT = "3306";
    /**
     * Define what source system will use
     * application properties or this.class
     */
    public static boolean USE_APPLICATION_PROPERTIES_DATA = true;

    /**
     * Do not start listener while testing
     * Do not change or test won't pass
     */
    public static boolean FALSE_WHILE_RUNNING_DB_TESTS = true;
}
