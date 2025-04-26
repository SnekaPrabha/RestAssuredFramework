package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.utils.BaseTest;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PostAPIRequestusingfile extends BaseTest{
	@Test
	 public void PostAPIRequestusingexternalfile() throws IOException {
		// TODO Auto-generated method stub
		 try {
			String PostAPIRequestBody=FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
			System.out.println(PostAPIRequestBody);
			
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
			
			
                  
			//API Chaining
			
			int bookingid = JsonPath.read(response.body().asString(),"$.bookingid");

			RestAssured
			   .given()
			      .contentType(ContentType.JSON)
			      .baseUri("https://restful-booker.herokuapp.com/booking/")
			   .when()
			      .get("{bookingid}", bookingid)
			      
			    .then()
			       .assertThat()
			       .statusCode(200);
			    
			       
			   
			       
			       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
