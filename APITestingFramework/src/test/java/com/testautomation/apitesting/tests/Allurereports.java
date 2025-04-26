package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.utils.BaseTest;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

@Epic("Epic -01")
@Feature("Create Update Delete Booking")
public class Allurereports extends BaseTest{
	private static final Logger logger = LogManager.getLogger(Allurereports.class);
	
	@Story("Story-01")
	@Test(description="end to end api testing")
	@Description("end to end testing")
	@Severity(SeverityLevel.CRITICAL)
	
	public void endtoendAPIRequest() throws IOException {
		// TODO Auto-generated method stub
		logger.info("The endtoendAPIRequest execution started");

		String PostAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
		String PutAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstants.PUT_API_REQUEST_BODY),"UTF-8");
		String tokenapigeneration=FileUtils.readFileToString(new File(FileNameConstants.TOKEN_REQUEST_BODY),"UTF-8");
		String patchAPIRequestbody=FileUtils.readFileToString(new File(FileNameConstants.PATCH_API_REQUEST_BODY),"UTF-8");

		System.out.println(PostAPIRequestBody);
		//POST Request
		Response response =
		RestAssured 
		
		    .given()
		       .contentType(ContentType.JSON)
		       .body(PostAPIRequestBody)
		       .baseUri("https://restful-booker.herokuapp.com/booking")
		     .when()
		        .post()
		     .then()
		        .assertThat()
		        .statusCode(200)
		
             .extract()
               .response();
		
		JSONArray jsonArrayfirstname = JsonPath.read(response.body().asString(),"$.booking..firstname");
		String firstname = (String) jsonArrayfirstname.get(0);
		System.out.println(firstname);

		//Validate the response
		Assert.assertEquals(firstname, "api testing");

		
		JSONArray jsonArrayCheckin = JsonPath.read(response.body().asString(),"$.booking.bookingdates..checkin");
		String checkin = (String) jsonArrayCheckin.get(0);
		System.out.println(checkin);

		//Validate the response
		Assert.assertEquals(checkin, "2018-01-01");
		
		//$['phonenumber'][1].type
		
		
		JSONArray jsonArrayadditionalneeds = JsonPath.read(response.body().asString(),"$.booking..additionalneeds");
		String jsonArrayadditionalneeds1 = (String) jsonArrayadditionalneeds.get(0);
		System.out.println(jsonArrayadditionalneeds1);
		
		//Validate the response
		Assert.assertEquals(jsonArrayadditionalneeds1, "super bowls");
		
		
              
		//To read the data from the JSON
		int bookingid = JsonPath.read(response.body().asString(),"$.bookingid");
		//Get Request

		RestAssured
		   .given()
		      .contentType(ContentType.JSON)
		      .baseUri("https://restful-booker.herokuapp.com/booking/")
		   .when()
		      .get("{bookingid}", bookingid)
		      
		    .then()
		       .assertThat()
		       .statusCode(200);
		
		
		
		//Token API Call
		
			Response TokenAPIResponse=
			RestAssured
			
			    .given()
			      .contentType(ContentType.JSON)
			      .body(tokenapigeneration)
			      .baseUri("https://restful-booker.herokuapp.com/auth")
			    .when() 
			      .post()
			    .then()	
			       .assertThat()
			       .statusCode(200)
			    .extract()
			       .response();
			
			
			String token = JsonPath.read(TokenAPIResponse.body().asString(), "$.token");
			//String path = response.path("token");
			//PUT API Request
			RestAssured
			   .given()
			     .contentType(ContentType.JSON)
			     .body(PutAPIRequestBody)
			     .headers("Cookie","token="+token)
			     .baseUri("https://restful-booker.herokuapp.com/booking")
			   .when() 
			     .put("/{bookingid}",bookingid)
			   .then() 
			      .assertThat()
			      .statusCode(200)
			      .body("firstname", Matchers.equalTo("Specflow"));
		
		//PATCH API Request
		
		RestAssured
		   .given()
		     .contentType(ContentType.JSON)
		     .body(patchAPIRequestbody)
		     .headers("Cookie","token="+token)
		     .baseUri("https://restful-booker.herokuapp.com/booking")
		   .when() 
		     .patch("/{bookingid}",bookingid)
		   .then() 
		      .assertThat()
		      .statusCode(200)
		      .body("firstname", Matchers.equalTo("Testers Talk"));
		
		

		//DELETE API Request
		
		RestAssured
		   .given()
		       .contentType(ContentType.JSON)
		       .headers("Cookie","token-"+token)
		       .baseUri("https://restful-booker.herokuapp.com/booking/")
		   .when()
		      .delete("/{bookingid}",bookingid)
		   .then()
		      .assertThat()
		      .statusCode(201);
		logger.info("The endtoendAPIRequest execution ended");

	}

}
