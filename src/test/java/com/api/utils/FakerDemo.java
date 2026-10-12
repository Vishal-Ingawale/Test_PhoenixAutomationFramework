package com.api.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDemo {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-IND"));
        // new Locale("en-IND") --> this is used for indian names
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        System.out.println(firstName);
        System.out.println(lastName);

        String buildingNumber = faker.address().buildingNumber();
        System.out.println(buildingNumber);
        String streetAddress = faker.address().streetAddress();
        System.out.println(streetAddress);
        String streetName = faker.address().streetName();
        System.out.println(streetName);
        String city = faker.address().city();
        System.out.println(city);

        System.out.println(faker.number().digits(10));
        System.out.println(faker.numerify("704#######"));
        System.out.println(faker.internet().emailAddress());
        
    }
}
