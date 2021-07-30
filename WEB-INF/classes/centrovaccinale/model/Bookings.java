package centrovaccinale.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import centrovaccinale.controller.sql.SQLrequest;

public class Bookings implements Serializable{
	private static final long serialVersionUID = 1199994135263623692L;
	private List<List<String>> resultComplete, resultTimeAndDate, resultPrencodeAndDate, resultDate;
	private String date, time, prencode;
	
	public Bookings() {
		date = null;
		time = null;
		prencode = null;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setPrencode(String prencode) {
		this.prencode = prencode;
	}
	
	public List<List<String>> searchComplete() {
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			LocalTime timeparsed = LocalTime.parse(time);
			resultComplete = SQLrequest.searchUsersBookingComplete(dateparsed, timeparsed, prencode);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return resultComplete;
	}

	public List<List<String>> searchByTimeAndDate() {
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			LocalTime timeparsed = LocalTime.parse(time);
			resultTimeAndDate = SQLrequest.searchUsersBookingByTimeAndDate(dateparsed, timeparsed);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return resultTimeAndDate;
	}
	
	public List<List<String>> searchByPrencodeAndDate() {
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			resultPrencodeAndDate = SQLrequest.searchUsersBookingByPrencodeAndDate(dateparsed, prencode);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return resultPrencodeAndDate;
	}
	
	public List<List<String>> searchByDate() {
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			resultDate = SQLrequest.searchUsersBookingByDate(dateparsed);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return resultDate;
	}
}
