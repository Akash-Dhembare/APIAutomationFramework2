package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class TestUpdateBookingPUT extends TestCreateBookingPOST {
    @Description("Verify that Update Booking is working fine")
    @Test
    public void testUpdateBooking(ITestContext iTestContext){

        iTestContext.setAttribute("token", getToken());
      String token=(String)iTestContext.getAttribute("token");
        System.out.println("Token is :" + token);

      //  System.out.println("Token is -> "+token);
        Integer bookingid= (Integer) iTestContext.getAttribute("bookingid");
        String basePut= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        requestSpecification.basePath(basePut);
        response= RestAssured.given(requestSpecification)
                .cookies("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println(response.asString());
    }
}
