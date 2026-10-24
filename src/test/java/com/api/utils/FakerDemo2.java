package com.api.utils;

import com.api.model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FakerDemo2 {
    public static void main(String[] args) {
    // Create fake CreateJobAPI Request payload

        Faker faker = new Faker(new Locale("en-IND"));

        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        String mobileNumber = faker.numerify("96########");
        String alternateMobileNumber = faker.numerify("96########");
        String customerEmailAddress = faker.internet().emailAddress();
        String altCustomerEmailAddress = faker.internet().emailAddress();

        Customer customer = new Customer(fname,lname,mobileNumber,alternateMobileNumber,customerEmailAddress,altCustomerEmailAddress);
        System.out.println(customer);

        String flatNumber = faker.numerify("###");
        String apartmentName = faker.address().streetName();
        String streetName = faker.address().streetName();
        String landmark = faker.address().streetName();
        String area = faker.address().streetName();
        String pinCode = faker.numerify("#####");
        String country = faker.address().country();
        String state = faker.address().state();

        CustomerAddress customerAddress = new CustomerAddress(flatNumber,apartmentName,streetName,landmark,area,pinCode,country,state);
        System.out.println(customerAddress);

        //CustomerProduct Fake Object
        String dop = DateTimeUtil.getTimeWithDaysAgo(10);
        String imeiSerialNumber = faker.numerify("###############");
        String popUrl = faker.internet().url();
        CustomerProduct customerProduct = new CustomerProduct(dop,imeiSerialNumber,imeiSerialNumber,imeiSerialNumber,popUrl,1,1);
        System.out.println(customerProduct);

        String fakeRemark = faker.lorem().sentence(5);
        //I want to generate random number between 1 to 27
        Random random = new Random();
        int problemId = random.nextInt(27)+1;
        Problems problems = new Problems(problemId,fakeRemark);
        System.out.println(problems);

        List<Problems> problemsList = new ArrayList<Problems>();
        problemsList.add(problems);

        CreateJobPayload payload = new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsList);
        System.out.println(payload);
    }
}
