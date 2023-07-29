package com.garguir.util;

import java.io.FileInputStream;
import java.util.Properties;

import static com.garguir.util.UtilExcelToList.PATH_RESOURCES;
import static com.garguir.util.UtilExcelToList.log;

public class Config {
    private static final String URL_FILE_ORIGIN = "urlFileOrigin";
    private static final String URL_FILE_DESTINY = "urlFileDestiny";
    private static final Properties PROPERTIES = new Properties();
    private static final String PATH_PROPERTIES = PATH_RESOURCES + "\\config.properties";
    private static Config config;

    private Config(){
        try{
            PROPERTIES.load(new FileInputStream(PATH_PROPERTIES));
            log().info("The config.properties file was opened successfully.");
        }catch(Exception e){
            log().warn(e.getMessage());
        }
    }

    public static Config getConfig(){
        if(config == null){
            config = new Config();
        }
        return config;
    }

    public String getUrlFileOrigin(){
        return PROPERTIES.getProperty(URL_FILE_ORIGIN);
    }

    public String getUrlFileDestiny(){
        return PROPERTIES.getProperty(URL_FILE_DESTINY);
    }
}
