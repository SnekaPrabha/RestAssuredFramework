package com.testautomation.apitesting.utils;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
	@BeforeMethod
	public void BeforeMethod() {
		// TODO Auto-generated method stub
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

	}

}
