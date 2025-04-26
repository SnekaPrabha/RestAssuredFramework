package com.testautomation.apitesting.pojos;

public class Booking {
	
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private Bookingdates bookingDates;
	
	public Booking()
	{
		
	}
	
	public Booking(String fname,String lname,int totalpri,boolean deppaid,Bookingdates bookingDates)
	{
		setFirstname(fname);
		setLastname(lname);
		setTotalprice(totalpri);
		setDepositpaid(deppaid);
		setBookingDates(bookingDates);
		
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public Bookingdates getBookingDates() {
		return bookingDates;
	}
	public void setBookingDates(Bookingdates bookingDates) {
		this.bookingDates = bookingDates;
	}
	
	

}
