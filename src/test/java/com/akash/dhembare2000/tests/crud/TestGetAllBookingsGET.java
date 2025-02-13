package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestGetAllBookingsGET extends BaseTest {

    @Description("Get all booking IDs")
    @Test
    public void testGetAllBookingIDs(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured.given(requestSpecification)
                .when().log().all().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
