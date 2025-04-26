package com.testautomation.apitesting.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class API_Chaining {
	@Test
public void API_chaining_concept() {
	// TODO Auto-generated method stub


		//prepare request body
				JSONObject booking = new JSONObject();
				JSONObject bookingDates = new JSONObject();
				
				booking.put("firstname", "api testing");
				booking.put("lastname", "tutorial");
				booking.put("totalprice", 1000);
				booking.put("depositpaid", true);
				booking.put("additionalneeds", "breakfast");
				booking.put("bookingdates", bookingDates);
				
				bookingDates.put("checkin", "2023-03-25");
				bookingDates.put("checkout", "2023-03-30");
				//POST Request
				Response response =
				RestAssured
						.given()
							.contentType(ContentType.JSON)
							.body(booking.toString())
							.baseUri("https://restful-booker.herokuapp.com/booking")
							//.log().all()
						.when()
							.post()
						.then()
							.assertThat()
							//.log().ifValidationFails()
							.statusCode(200)
							.body("booking.firstname", Matchers.equalTo("api testing"))
							.body("booking.totalprice", Matchers.equalTo(1000))
							.body("booking.bookingdates.checkin", Matchers.equalTo("2023-03-25"))
						.extract()
							.response();
				
       int bookingID=response.path("bookingID");
				
		//Reponse of the 1st request		
				
       RestAssured
          .given()
             .contentType(ContentType.JSON)
             .pathParam("bookingID", bookingID)
             .baseUri("https://restful-booker.herokuapp.com/booking")
          .when()
             .get("{bookingID}")
           .then()
              .assertThat()
              .statusCode(200);
       
       
       
              
             
		
		


}
}
