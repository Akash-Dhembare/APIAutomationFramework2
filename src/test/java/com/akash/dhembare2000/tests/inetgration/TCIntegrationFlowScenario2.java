package com.akash.dhembare2000.tests.inetgration;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.listeners.RetryAnalyzer;
import com.akash.dhembare2000.pojos.BookingResponse;
import com.akash.dhembare2000.utils.PropertyReader;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegrationFlowScenario2 extends BaseTest {
    // Create A Booking and Create a Token
    // Delete the Booking
    // Get booking Verify (404)

    @Test(groups = "integration", priority = 1)
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);
//        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));

        // DeSer
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));


        //  Set the booking ID
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "integration", priority = 2)
    @Description("TC#INT2 - Step 2. Verify that the Delete Booking")
    public void testDeleteBooking(ITestContext iTestContext){
        String token=(String) iTestContext.getAttribute("token");
        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
        response= RestAssured.given(requestSpecification)
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .when().delete();

        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(201);


    }

    @Test(groups = "integration", priority = 3)
    @Description("TC#INT1 - Step 3. Verify that the Deleted Booking can not be shown")
    public void testDeleteBookingGet(ITestContext iTestContext){
        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);

        response=RestAssured.given(requestSpecification)
                .when().log().all().get();

        validatableResponse=response.then().log().all();
       // validatableResponse.statusCode(200);
        validatableResponse.statusCode(404);
    }

}
