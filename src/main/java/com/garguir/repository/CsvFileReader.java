package com.garguir.repository;

import com.garguir.models.Document;
import com.garguir.util.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.garguir.util.UtilExcelToList.*;

public class CsvFileReader {
    private static final String PATH = Config.getConfig().getUrlCsvFileOrigin();
    private static CsvFileReader instance = null;

    public static CsvFileReader getInstance(){
        if(instance == null){
            instance = new CsvFileReader();
        }
        return instance;
    }

    public List<Document> readFile() {
        int i=0;
        List<Document> documents = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            String line = bufferedReader.readLine();
            while(line != null){
                String[] splitLine = line.split(SEMICOLON);
                documents.add(new Document(addZeroForward(splitLine[0].trim()), deleteLeftZeros(splitLine[1])));
                log().info(documents.get(i).getType() + " - " + documents.get(i).getNumber());
                line = bufferedReader.readLine();
                i++;
            }
        } catch (IOException e) {
            log().warn(e.getMessage());
        }

        return documents;
    }
}
