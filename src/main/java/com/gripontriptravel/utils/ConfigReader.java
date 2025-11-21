package com.gripontriptravel.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties prop;

    public static void loadConfig(){
        if(prop==null){
            try {
                prop = new Properties();
                FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        if (prop==null){
            loadConfig();
        }
        return prop.getProperty(key);
    }
}

