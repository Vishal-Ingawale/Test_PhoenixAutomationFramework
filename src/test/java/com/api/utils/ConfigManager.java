package com.api.utils;

import java.io.*;
import java.util.Properties;
public class ConfigManager {
    private static Properties properties = new Properties();
    private static String path = "config/config.properties";
    private static String env;

    private ConfigManager() {  //constructor
    }

    static {
        env = System.getProperty("env");
        switch (env){
            case "dev":{
                path = "config/config.dev.properties";
                break;
            }
            case "qa":{
                path = "config/config.qa.properties";
                break;
            }
            case "uat":{
                path = "config/config.uat.properties";
                break;
            }
        }

        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (input == null){
            throw new RuntimeException("Cannot find the file at the path: "+ path);
        }
        try {
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

