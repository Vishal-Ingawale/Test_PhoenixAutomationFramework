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
        env = System.getProperty("env","qa");
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
            default:
                path = "config/config.qa.properties";
        }

        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (input == null){
            throw new RuntimeException("Cannot find the file at the path: "+ path);
        }
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: "+path,e);
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

