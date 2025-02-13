package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestDeleteBookingDELETE extends TestCreateBookingPOST{
    @Description("Test that delete booking is working fine")
    @Test
    public void testDeleteBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());
        String token=(String)iTestContext.getAttribute("token");
        System.out.println("Token is :" + token);

        //  System.out.println("Token is -> "+token);
        Integer bookingid= (Integer) iTestContext.getAttribute("bookingid");
        String baseDelete= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(baseDelete);


        requestSpecification.basePath(baseDelete);
        validatableResponse= RestAssured.given(requestSpecification)
                .cookies("token",token).when().delete().then().log().all();

        validatableResponse.statusCode(201);


    }
}
