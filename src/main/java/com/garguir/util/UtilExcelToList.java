package com.garguir.util;

import com.garguir.models.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UtilExcelToList {
    public static final Logger log = LogManager.getLogger("ExcelToList");
    public static final String WITHOUT_EXTENSION = ".without_extension";
    public static final String FILE_NAME = "users";
    public static final String ENVIRONMENT = "dev";
    public static final String SEMICOLON = ";";
    public static final String LINE_BREAK = "\n";
    public static final String CSV = ".csv";
    public static final String HYPHEN = "-";
    public static final String URL_BAR = "/";
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

    public static String addZeroForward(String s){
        return (s.length() > 1) ? s : "0" + s;
    }

    public static String deleteLeftZeros(String s){
        return s.replaceFirst( "^0+(?!$)", "");
    }

    public static Long getUsageMemory(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static StringBuilder documentsToStringBuilder(List<Document> documents){
        StringBuilder stringBuilder = new StringBuilder();

        for (Document document : documents) {
            stringBuilder.append(document.getType());
            stringBuilder.append(SEMICOLON);
            stringBuilder.append(document.getNumber());
            stringBuilder.append(LINE_BREAK);
        }

        return stringBuilder;
    }
}
