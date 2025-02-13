package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.pojos.Booking;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.akash.dhembare2000.tests.crud.TestCreateBookingPOST.*;

public class TestGetBookingGET extends TestCreateBookingPOST{


    @Description("Verify that the Get Booking Details by Booking ID is Working")
    @Test (dependsOnMethods = "testVerifyCreateBookingPOST01")
    public void testGetBookingDetailsByID(ITestContext iTestContext){





        Integer bookingid=(Integer)iTestContext.getAttribute("bookingid");

        String basePathGetBooking= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingid;
        System.out.println(basePathGetBooking);


        requestSpecification=RestAssured.given()
                .baseUri(APIConstants.BASE_URL).basePath(basePathGetBooking);

        response= requestSpecification
                .when().log().all().get();

        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

    }

}
