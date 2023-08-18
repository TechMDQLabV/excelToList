package com.garguir.repository;

import com.garguir.models.Document;
import com.garguir.util.Config;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.garguir.util.UtilExcelToList.log;

public class ExcelFileReader {
    private static final String PATH = Config.getConfig().getUrlXlsxFileOrigin();
    private static ExcelFileReader instance = null;

    public static ExcelFileReader getInstance() {
        if(instance == null) {
            instance = new ExcelFileReader();
        }
        return instance;
    }

    public List<Document> readFile() {
        List<Document> documents = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(PATH);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            DataFormatter formatter = new DataFormatter();
            while(rowIterator.hasNext()){

                Row nextRow = rowIterator.next();
                documents.add(new Document(formatter.formatCellValue(nextRow.getCell(0)), formatter.formatCellValue(nextRow.getCell(1))));
                log().info(formatter.formatCellValue(nextRow.getCell(0)) + " - " + formatter.formatCellValue(nextRow.getCell(1)));
                Thread.sleep(350);
            }
        } catch (IOException e) {
            log().warn(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return documents;
    }
}
