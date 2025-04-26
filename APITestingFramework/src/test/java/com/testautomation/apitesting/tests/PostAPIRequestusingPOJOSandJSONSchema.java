package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testautomation.apitesting.pojos.Booking;
import com.testautomation.apitesting.pojos.Bookingdates;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostAPIRequestusingPOJOSandJSONSchema {
	
	@Test
	public void PostAPIRequest() throws IOException {
		try {
			// TODO Auto-generated method stub
			
			String jsonschema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA),"UTF-8");
			Bookingdates bookingDates = new Bookingdates("2018-01-01", "2019-01-01");
			Booking booking =new Booking("api testing", "tutorial", 1000, true, bookingDates);
			//Serialization
			ObjectMapper objectmapper =new ObjectMapper();
			String requestbody = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
			System.out.println(requestbody);
			
			
			
			//De-serialization
			Booking bookingdetails = objectmapper.readValue(requestbody, Booking.class);
			System.out.println(bookingdetails.getFirstname());
			System.out.println(bookingdetails.getLastname());
			System.out.println(bookingdetails.getTotalprice());
			System.out.println(bookingdetails.isDepositpaid());
			System.out.println(bookingdetails.getBookingDates().getCheckin());
			System.out.println(bookingdetails.getBookingDates().getCheckout());
			
			Response response=
			RestAssured
			   .given()
			      .contentType(ContentType.JSON)
			      .body(requestbody)
			      .baseUri("https://restful-booker.herokuapp.com/booking")
			  .when()
			      .post()
			      
			  .then()
			      .assertThat()
			      .statusCode(200)
		      .extract()
		          .response();
		          
		     int bookingid = response.path("bookingid");  
			// System.out.println(jsonschema);

		     RestAssured
		        .given()
		           .contentType(ContentType.JSON)
		           .baseUri("https://restful-booker.herokuapp.com/booking")
		        .when()
		           .get("/{bookingid}",bookingid)
		         .then()  
		         
		           .assertThat()
		           .statusCode(200)
		           .body(JsonSchemaValidator.matchesJsonSchema(jsonschema)); //To validate the Expected JSON Schema
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		



		
		
		
		

	}

}
