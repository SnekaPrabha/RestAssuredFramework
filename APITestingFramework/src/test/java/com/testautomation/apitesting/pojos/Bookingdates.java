package com.testautomation.apitesting.pojos;

public class Bookingdates {

	private String checkin;
	private String checkout;

	public Bookingdates() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Bookingdates(String cin,String cout)
	{
		setCheckin(cin);
		setCheckout(cout);
	}
	
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
}
