package com.garguir.repository;

import com.garguir.models.Document;
import com.garguir.util.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.garguir.util.UtilExcelToList.CSV;
import static com.garguir.util.UtilExcelToList.ENVIRONMENT;
import static com.garguir.util.UtilExcelToList.FILE_NAME;
import static com.garguir.util.UtilExcelToList.HYPHEN;
import static com.garguir.util.UtilExcelToList.PATH_RESOURCES;
import static com.garguir.util.UtilExcelToList.SEMICOLON;
import static com.garguir.util.UtilExcelToList.URL_BAR;
import static com.garguir.util.UtilExcelToList.WITHOUT_EXTENSION;
import static com.garguir.util.UtilExcelToList.addZeroForward;
import static com.garguir.util.UtilExcelToList.deleteLeftZeros;
import static com.garguir.util.UtilExcelToList.documentsToStringBuilder;
import static com.garguir.util.UtilExcelToList.log;

public class CsvSplitter {
    private static final String PATH = Config.getConfig().getUrlCsvFileOrigin();
    private static final int LIMIT = 10;
    private static CsvSplitter instance = null;

    public static CsvSplitter getInstance(){
        if(instance == null){
            instance = new CsvSplitter();
        }
        return instance;
    }
    public void readFile() {
        int contLines = 0;
        int contFiles = 1;
        List<Document> documents = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line = bufferedReader.readLine();
            while(line != null){
                if(contLines == LIMIT){
                    contLines = 0;
                    writeFile(documents, contFiles);
                    documents.clear();
                    contFiles++;
                }
                String[] splitLine = line.split(SEMICOLON);
                documents.add(new Document(addZeroForward(splitLine[0].trim()), deleteLeftZeros(splitLine[1])));
                log().info(documents.get(contLines).getType() + " - " + documents.get(contLines).getNumber());
                line = bufferedReader.readLine();
                contLines++;
            }
            writeFile(documents, contFiles);
            documents.clear();
        } catch (IOException e) {
            log().warn(e.getMessage());
        }
    }

    private void writeFile(List<Document> documents, int cont){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_RESOURCES + URL_BAR + FILE_NAME + HYPHEN + ENVIRONMENT + HYPHEN + cont + CSV));

            StringBuilder stringBuilder = documentsToStringBuilder(documents);

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            log().info("Archivo .csv guardado");
        }catch (IOException ioException){
            log().warn("Error: Fallo de escritura del archivo .csv " + ioException);
        }
    }

    private String getFileExtension(String fileName){
        int ext = fileName.lastIndexOf(".");
        if(ext!=-1){
            return fileName.substring(ext);
        }else{
            return WITHOUT_EXTENSION;
        }
    }
}
