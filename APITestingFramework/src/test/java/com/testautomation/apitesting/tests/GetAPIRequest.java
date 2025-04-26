package com.testautomation.apitesting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAPIRequest {
	@Test
	public void getAllBookingsIds() {
		// TODO Auto-generated method stub
		
		Response response=
		RestAssured
		   .given()
		      .contentType(ContentType.JSON)
		      .baseUri("https://restful-booker.herokuapp.com/booking")
		   .when()
		      .get()
		      
		   .then()
		       .statusCode(200)
		       .header("content-type", "application/json; charset=utf-8")
		   .extract()
		       .response();
		
		
		
		  Assert.assertTrue(response.getBody().asString().contains("bookingid"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Response response=
//		RestAssured
//		        .given()
//		           .contentType(ContentType.JSON)
//		           .baseUri("https://restful-booker.herokuapp.com/booking")
//		        .when()
//		           .get()
//		        .then()
//		           .statusCode(200)
//		           .statusLine("HTTP/1.1 200 OK")
//		           .header("Content-Type", "application/json; charset=utf-8")
//		        .extract()
//		           .response();
//		           
//		Assert.assertTrue(response.getBody().asString().contains("bookingID"));         
		   

	}

}
