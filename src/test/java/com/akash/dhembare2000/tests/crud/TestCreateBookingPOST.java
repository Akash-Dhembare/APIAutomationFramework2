package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestCreateBookingPOST extends BaseTest {

    @Owner("Akash Dhembare")
    @Severity(SeverityLevel.BLOCKER)
    @Description ("Verify that POST request is working fine.")
    @Test
    public void testVerifyCreateBookingPOST01(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                        .body(payloadManager.createPayloadBookingAsString()).when().post();

        validatableResponse = response.then().log().all().statusCode(200);

        // Default Hamcrest - Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo("Akash"));

        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBookingid()).isNotZero().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Akash");


       // TestNG Assertions
        assertActions.verifyStatusCode(response, 200);
    }
}
