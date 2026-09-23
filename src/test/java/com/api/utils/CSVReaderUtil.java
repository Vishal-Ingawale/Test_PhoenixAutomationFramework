package com.api.utils;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReaderUtil {
    //Util class has private constructor
    //Static methods
    //the Job is to help me read the csv file and map it a bean

    private CSVReaderUtil(){
        //No one can create object of CSVReaderUtil outside the class
        //Singleton class constructor are private
    }
    public static void loadCSV(String pathOfCSVFile) {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
                .withType(UserBean.class)
                .withIgnoreEmptyLine(true)
                .build();

        List<UserBean> userList=csvToBean.parse();
        System.out.print(userList);
    }
}
