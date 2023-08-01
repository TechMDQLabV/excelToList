package com.garguir;

import com.garguir.models.Document;
import com.garguir.repository.ExcelFileReader;
import com.garguir.repository.TxtFileWriter;

import java.util.ArrayList;
import java.util.List;
import static com.garguir.util.UtilExcelToList.log;
public class Main {
    public static void main(String[] args) {
        log().warn("Comenzamos");
        List<Document> documentsToErase = ExcelFileReader.getInstance().readFile();
        //TODO recorrer la lista y eliminar los usuarios, esto retorna una lista de documentos que no se eliminaron
        List<Document> documentsNotErased = new ArrayList<>();
        TxtFileWriter.getInstance().saveDocumentsNotErased(documentsNotErased);
    }
}