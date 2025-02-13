package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestPartialUpdateBookingPATCH extends TestCreateBookingPOST{

    @Description("Verify that Partial update is working fine")
    @Test
    public void testPartialUpdate(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());
        String token=(String)iTestContext.getAttribute("token");
        System.out.println("Token is :" + token);

        //  System.out.println("Token is -> "+token);
        Integer bookingid= (Integer) iTestContext.getAttribute("bookingid");
        String basePatch= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        requestSpecification.basePath(basePatch);
        response= RestAssured.given(requestSpecification)
                .cookies("token",token)
                .when().body(payloadManager.partialUpdatePayloadAsString()).patch();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println(response.asString());
    }
}
