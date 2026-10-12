package com.api.utils;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


public class CSVReaderUtil {
    //Util class has private constructor
    //Static methods
    //the Job is to help me read the csv file and map it a bean
    private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtil.class);

    private CSVReaderUtil(){
        //No one can create object of CSVReaderUtil outside the class
        //Singleton class constructor are private
    }

    public static <T> Iterator<T> loadCSV(String pathOfCSVFile, Class<T> bean) {
        LOGGER.info("Loading the CSV file from the path {}", pathOfCSVFile);

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader csvReader = new CSVReader(isr);

        LOGGER.info("Converting the csv to Bean class {}", bean);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
                .withType(bean)
                .withIgnoreEmptyLine(true)
                .build();

        List<T> list = csvToBean.parse();
        return list.iterator();
    }
}
