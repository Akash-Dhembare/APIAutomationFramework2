package com.akash.dhembare2000.tests.ddt.vwo;

import com.akash.dhembare2000.utils.UtilsExcel;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class VWOLoginAPIDDT {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    Integer ID;

    @Test(dataProvider = "getData", dataProviderClass= UtilsExcel.class)
    public void testVWOLogin(String email, String password){
        System.out.println("-- Login API Testing --");
        System.out.println(email);
        System.out.println(password);
    }
}
