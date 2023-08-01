package com.garguir.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilExcelToList {
    private static final Logger log = LogManager.getLogger("ExcelToList");
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String PATH_RESOURCES = USER_DIR + "\\src\\main\\resources";

    private UtilExcelToList(){
    }

    public static Logger log(){
        return log;
    }

    public static String getStringDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
