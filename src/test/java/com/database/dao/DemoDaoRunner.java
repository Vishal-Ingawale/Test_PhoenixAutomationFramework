package com.database.dao;

import com.api.model.Customer;
import com.database.model.CustomerDBModel;
import org.testng.Assert;

import java.sql.SQLException;

public class DemoDaoRunner {
    public static void main(String[] args) throws SQLException {
       CustomerDBModel customerDBData = CustomerDao.getCustomerInfo();
        System.out.println(customerDBData.getFirst_name());
        System.out.println(customerDBData.getLast_name());
        System.out.println(customerDBData.getMobile_number());
        System.out.println(customerDBData.getEmail_id());
        Customer customer = new Customer("Hailey","Klocko","543-413-0191","","Kristy8@yahoo.com","");
        System.out.println(customer.getFirst_name());
        Assert.assertEquals(customerDBData.getFirst_name(),customer.getFirst_name());
    }
}
