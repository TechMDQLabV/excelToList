package com.garguir.repository;

import com.garguir.models.Document;
import com.garguir.util.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.garguir.util.UtilExcelToList.*;
import static com.garguir.util.UtilExcelToList.log;

public class CsvSplitter {
    private static final String PATH = Config.getConfig().getUrlCsvFileOrigin();
    private static final String WITHOUT_EXTENSION = ".without_extension";
    private static final String FILE_NAME = "users";
    private static final String ENVIRONMENT = "dev";
    private static final String SEMICOLON = ";";
    private static final String LINE_BREAK = "\n";
    private static final String CSV = ".csv";
    private static final String HYPHEN = "-";
    private static final int LIMIT = 10;
    private static CsvSplitter instance = null;

    public static CsvSplitter getInstance(){
        if(instance == null){
            instance = new CsvSplitter();
        }
        return instance;
    }

    public List<Document> readFile() {
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

        return documents;
    }

    private void writeFile(List<Document> documents, int cont){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_RESOURCES + URL_BAR + FILE_NAME + HYPHEN + ENVIRONMENT + HYPHEN + cont + CSV));

            for(Document document : documents) {
                stringBuilder.append(document.getType());
                stringBuilder.append(SEMICOLON);
                stringBuilder.append(document.getNumber());
                stringBuilder.append(LINE_BREAK);
            }
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
