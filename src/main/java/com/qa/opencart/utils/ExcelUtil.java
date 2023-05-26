package com.qa.opencart.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Let's take note using Excel sheet is not  used any more for the Modern Framework!!! Might get corrupted
// Not active community org.apache.poi

public class ExcelUtil { //POM_8_ExcelUtil_POI_API_TESTRuNNER_testngXmlFiles_ParallelTest

    private static final String TEST_DATA_SHEET_PATH = "src\\main\\resources\\testdata\\OpenCartTestData.xlsx"; //YOU CAN ADD THIS ON APPCONSTANT
    private static Workbook book;
    private static Sheet sheet; //import poi.ss


    public static Object[][] getTestData(String sheetName) {

        System.out.println("Reading the data from sheet: " + sheetName);

        Object data[][] = null;

        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

            data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

                for (int i = 0; i < sheet.getLastRowNum(); i++) { //21:18_POM_8
                 for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                        data[i][j] = sheet.getRow(i+1).getCell(j).toString(); //[ROW][COLUMN]
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
