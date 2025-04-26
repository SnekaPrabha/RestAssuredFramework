package com.testautomation.apitesting.tests;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class PostAPIRequest {
	
	@Test
	public void Postapirequests() {
		// TODO Auto-generated method stub
		
		//Prepare request body
	JSONObject sample1=new JSONObject();
	sample1.put("sample", "StringValue1");
	sample1.put("id", 1);

	
	JSONObject sample2=new JSONObject();
	sample2.put("sample", "StringValue2");
	sample2.put("sample",sample1 );

	
	
	
       RestAssured
         .given()
           .contentType(ContentType.JSON)
           .body(sample2.toString())
           .baseUri("https://restful-booker.herokuapp.com/booking")
           .log().body() //to log the request body 
           .log().headers() //to print the headers which we have used
           .log().all() //to print all the headers , request body which we have used
                  
         .when()
           .post()
         .then()
            .assertThat()
            .log().body() //to print the response in the console
            .log().headers()
            .log().ifValidationFails() //to print the validation fail scenarios
            .statusCode(200)
            .body("booking.firstname", Matchers.equalTo("kala")); //to verify the details from the response
       
          
		
		

		
		
		
	}

}
