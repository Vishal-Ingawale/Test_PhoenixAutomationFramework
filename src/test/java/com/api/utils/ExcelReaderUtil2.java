package com.api.utils;

import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtil2 {

    private ExcelReaderUtil2() {

    }

    public static <T> Iterator<T> loadTestData(String xlsxFile, String sheetName, Class<T> clazz) {
        //Apache POI OOXML LIB
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
        XSSFWorkbook myWorkBook = null;
        try {
            myWorkBook = new XSSFWorkbook(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet mySheet = myWorkBook.getSheet(sheetName); //"LoginTestData"
        List<T> dataList = Poiji.fromExcel(mySheet, clazz);
        return dataList.iterator();
    }
}
