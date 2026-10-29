package com.api.utils;

import com.api.model.UserCredentials;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReaderUtil2 {

    private ExcelReaderUtil2(){

    }
    public static Iterator<UserCredentials> loadTestData() {
        //Apache POI OOXML LIB
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
        XSSFWorkbook myWorkBook = null;
        try {
            myWorkBook = new XSSFWorkbook(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");

        XSSFRow headerRows=mySheet.getRow(0);
        int usernameIndex=-1;
        int passwordIndex=-1;

        for (Cell cell:headerRows){
            if (cell.getStringCellValue().trim().equalsIgnoreCase("username")){
                usernameIndex=cell.getColumnIndex();
            }
            if (cell.getStringCellValue().trim().equalsIgnoreCase("password")){
                passwordIndex=cell.getColumnIndex();
            }
        }
        System.out.println(usernameIndex+" "+passwordIndex);

        int lastRowIndex=mySheet.getLastRowNum();
        XSSFRow rowData;
        ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
        for (int rowIndex=1; rowIndex<=lastRowIndex;rowIndex++){
            rowData=mySheet.getRow(rowIndex);
            UserCredentials userCredentials = new UserCredentials(rowData.getCell(usernameIndex).toString(),rowData.getCell(passwordIndex).toString());
            userList.add(userCredentials);
        }
        return userList.iterator();
    }
}
