package com.akash.dhembare2000.tests.inetgration;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.pojos.Booking;
import com.akash.dhembare2000.pojos.BookingResponse;
import com.akash.dhembare2000.utils.PropertyReader;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import static org.assertj.core.api.Assertions.assertThat;

@Test(retryAnalyzer = RetryAnalyzerCount.class)
public class TCIntegrationFlowRetry extends BaseTest {

    @Test(groups = "integration", priority = 1)
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

        // DeSer
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "integration", priority = 2)
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingByID(ITestContext iTestContext){
        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");

        // GET Req
        String basePathGET=APIConstants.CREATE_UPDATE_BOOKING_URL + "/"+ bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);

        response= RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking=payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));
    }

    @Test(groups = "integration", priority = 3)
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
        System.out.println("Token -> "+iTestContext.getAttribute("token"));
        String token= (String) iTestContext.getAttribute("token");

        Integer bookingid=(Integer) iTestContext.getAttribute("bookingid");

        // PUT Req
        String basePathPUT= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        requestSpecification.basePath(basePathPUT);

        response=RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking= payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.put.lastname"));

    }

    @Test(groups = "integration", priority = 4)
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingByID(ITestContext iTestContext){
        String token= (String) iTestContext.getAttribute("token");
        Integer bookingid= (Integer) iTestContext.getAttribute("bookingid");

        // DELETE Req
        String basePathDELETE=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;

        requestSpecification.basePath(basePathDELETE);

        response=RestAssured.given(requestSpecification)
                .cookie("token", token)
                .when().delete();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
