package com.akash.dhembare2000.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    // Responsibility of the Class is to give the value of by Key

    public static String readKey(String key){
        Properties properties=new Properties();
        // Legacy 1.2 JDK, - old

        try {
            FileInputStream fileInputStream= new FileInputStream("src/test/resources/data.properties");
            properties.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties.getProperty(key);
    }

    public static Integer readKey(Integer key){
        Properties properties=new Properties();
        // Legacy 1.2 JDK, - old

        try {
            FileInputStream fileInputStream= new FileInputStream("src/test/resources/data.properties");
            properties.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.valueOf(properties.getProperty(String.valueOf(key)));
    }
}
