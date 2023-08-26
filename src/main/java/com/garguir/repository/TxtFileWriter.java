package com.garguir.repository;

import com.garguir.models.Document;
import com.garguir.util.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.garguir.util.UtilExcelToList.documentsToStringBuilder;
import static com.garguir.util.UtilExcelToList.getStringDate;
import static com.garguir.util.UtilExcelToList.log;

public class TxtFileWriter {
    private static final String PATH = Config.getConfig().getUrlFileDestiny();
    private static final String FILE_NAME = "-documentsNotErased.csv";
    private static TxtFileWriter instance = null;

    public static TxtFileWriter getInstance(){
        if(instance == null){
            instance = new TxtFileWriter();
        }
        return instance;
    }

    public void saveDocumentsNotErased(List<Document> documents) {
        if(!documents.isEmpty()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH+getFileName()));

                StringBuilder stringBuilder = documentsToStringBuilder(documents);

                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.close();
                log().info("File saved succesfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getFileName(){
        return getStringDate()+FILE_NAME;
    }
}
