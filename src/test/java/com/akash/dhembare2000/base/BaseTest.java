package com.akash.dhembare2000.base;

import com.akash.dhembare2000.asserts.AssertActions;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // Common to all test cases
    // Base Test (Father) -> Testcase (Son) - Single Inheritance
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;

    public AssertActions assertActions;
    public JsonPath jsonPath;
    public PayloadManager payloadManager;

    @BeforeTest
    public void setUp(){
        payloadManager= new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }
}
