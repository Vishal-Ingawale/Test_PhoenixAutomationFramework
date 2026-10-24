package com.api.utils;

import com.api.model.*;
import com.github.javafaker.Faker;

import java.util.*;

public class FakerDataGenerator {
    private static Faker faker = new Faker(new Locale("en-IND"));
    private final static int MST_SERVICE_LOCATION_ID = 0;
    private final static int MST_PLATFORM_ID = 2;
    private final static int MST_WARRANTY_STATUS_ID = 1;
    private final static int MST_OEM_ID = 1;
    private final static int PRODUCT_ID = 1;
    private final static int MST_MODEL_ID = 1;

    private FakerDataGenerator() {
    }

    public static CreateJobPayload generateFakeCreateJobData() {
        Customer customer = generateFakeCustomerData();
        CustomerAddress customerAddress = generateFakeCustomerAddressData();
        CustomerProduct customerProduct = generateFakeCustomerProduct();
        List<Problems> problemsList = generateFakeProblemsList();
        CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID,MST_PLATFORM_ID,MST_WARRANTY_STATUS_ID,MST_OEM_ID,customer,customerAddress,customerProduct,problemsList);
        return payload;
    }

    public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
        List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
        for (int i=1; i<=count; i++) {
            Customer customer = generateFakeCustomerData();
            CustomerAddress customerAddress = generateFakeCustomerAddressData();
            CustomerProduct customerProduct = generateFakeCustomerProduct();
            List<Problems> problemsList = generateFakeProblemsList();
            CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
            payloadList.add(payload);
        }
        return payloadList.iterator();
    }

    private static List<Problems> generateFakeProblemsList() {
        Random random = new Random();
        int problemId = random.nextInt(27)+1;
        String fakeRemark = faker.lorem().sentence(5);
        Problems problems = new Problems(problemId,fakeRemark);

        List<Problems> problemsList = new ArrayList<Problems>();
        problemsList.add(problems);
        return problemsList;
    }

    private static CustomerProduct generateFakeCustomerProduct() {
        String dop = DateTimeUtil.getTimeWithDaysAgo(10);
        String imeiSerialNumber = faker.numerify("###############");
        String popUrl = faker.internet().url();

        CustomerProduct customerProduct = new CustomerProduct(dop,imeiSerialNumber,imeiSerialNumber,imeiSerialNumber,popUrl,PRODUCT_ID,MST_MODEL_ID);
        return customerProduct;
    }

    private static CustomerAddress generateFakeCustomerAddressData() {
        String flatNumber = faker.numerify("###");
        String apartmentName = faker.address().streetName();
        String streetName = faker.address().streetName();
        String landmark = faker.address().streetName();
        String area = faker.address().streetName();
        String pinCode = faker.numerify("#####");
        String country = faker.address().country();
        String state = faker.address().state();

        CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pinCode, country, state);
        return customerAddress;
    }

    private static Customer generateFakeCustomerData() {
        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        String mobileNumber = faker.numerify("96########");
        String alternateMobileNumber = faker.numerify("96########");
        String customerEmailAddress = faker.internet().emailAddress();
        String altCustomerEmailAddress = faker.internet().emailAddress();

        Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddress, altCustomerEmailAddress);
        return customer;
    }

}
