package com.paylocity.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigValues {

    static Properties properties=null;

    public static String getProperty(String propertyName){
        try {
            FileInputStream file = new FileInputStream(new File("config.properties")) ;
            try {
                properties= new Properties();
                properties.load(file);
                return properties.getProperty(propertyName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
