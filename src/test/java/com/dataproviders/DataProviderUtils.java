package com.dataproviders;

import com.api.model.CreateJobPayload;
import com.api.model.UserCredentials;
import com.api.utils.*;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "LoginAPIDataProvider", parallel = true)
    public static Iterator<UserBean> loginAPIDataProvider() {
        return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
    }

    @DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
    public static Iterator<CreateJobPayload> createJobDataProvider() {
        Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);

        List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
        CreateJobBean tempBean;
        CreateJobPayload tempPayload;
        while (createJobBeanIterator.hasNext()) {
            tempBean = createJobBeanIterator.next();
            tempPayload = CreateJobBeanMapper.mapper(tempBean);
            payloadList.add(tempPayload);
        }
        return payloadList.iterator();
    }

    @DataProvider(name = "CreateJobAPIFakerDataProvider", parallel = true)
    public static Iterator<CreateJobPayload> createJobFakerDataProvider() {
      Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(10);
      return payloadIterator;
    }

    @DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
    public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
        return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredentials[].class);
    }

    @DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
        return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);
    }

    @DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
    public static Iterator<UserBean> loginAPIExcelDataProvider() {
        return ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx","LoginTestData", UserBean.class);
    }

    @DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {
        Iterator<CreateJobBean> iterator = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx","CreateJobTestData", CreateJobBean.class);

        List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
        CreateJobBean tempBean;
        CreateJobPayload tempPayload;
        while (iterator.hasNext()) {
            tempBean = iterator.next();
            tempPayload = CreateJobBeanMapper.mapper(tempBean);
            payloadList.add(tempPayload);
        }
        return payloadList.iterator();
    }
}
