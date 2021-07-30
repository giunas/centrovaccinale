package centrovaccinale.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import centrovaccinale.controller.sql.SQLrequest;

public class Vaccines implements Serializable {
	private static final long serialVersionUID = -3001398081655760004L;
	private String prencode, email, date, time, vaccine, reason;
	
	public Vaccines() {
		prencode = null;
		email = null;
		date = null;
		time = null;
		vaccine = null;
		reason = null;
	}
	
	public void setPrencode(String prencode) {
		this.prencode = prencode;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int insertlist() {
		int upd = 0;
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			LocalTime timeparsed = LocalTime.parse(time);
			upd = SQLrequest.insertVaccine(prencode, email, dateparsed, timeparsed, vaccine, reason);
			
			//se upd = 1 inserisco in lista
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return upd;
	}
	
	public String searchVaccines(String startdate, String finishdate) {
		String xml = null;
		try {
			Date startdateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date finishdateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(finishdate);
			xml = SQLrequest.searchVaccines(startdateparsed, finishdateparsed);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return xml;		
	}
}
