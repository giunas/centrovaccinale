package centrovaccinale.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import centrovaccinale.controller.sql.SQLrequest;
import centrovaccinale.security.SHA;

public class BookingBean implements Serializable {
	private static final long serialVersionUID = 2111737221730538037L;
	private String cf;
	private String prencode;
	private List<List<String>> result;
	
	public BookingBean() {
		cf = null;
		prencode = null;
		result = null;
	}
	
	public void setCF(String cf) {
		this.cf = cf;
	}
	
	public void setPrencode(String prencode) {
		this.prencode = prencode;
	}
	
	public List<List<String>> getResult() {
		return result;
	}
	
	public int login() {
		result = SQLrequest.searchUserBooking(cf, prencode);
		return result.size();
	}
	
	public int editlist(String cf, String prencode, String date, String time, String phone) {
		int upd = 0;
		try {
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			LocalTime timeparsed = LocalTime.parse(time);
			upd=SQLrequest.editUserBooking(cf, prencode, dateparsed, timeparsed, phone);
			if(upd == 1) {
				//modifico data, ora e telefono di result
				result.get(1).set(1, date);
				result.get(1).set(2, time);
				result.get(1).set(11, phone);
			}
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		return upd;
	}
	
	public int deletelist(String cf, String prencode) {
		int upd = 0;
		upd = SQLrequest.deleteUserBooking(cf, prencode);
		if(upd == 1) {
			result = null;
		}
		return upd;
	}
	
	public String insertlist(String date, String time, String cat, String spec, String pat, String cf,
							String name, String surname, String residence, String street, String phone) {
		int upd = 0;
		String prencode = "";
		try {
			SHA.setprencode(cf+date+time);
			Date dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			LocalTime timeparsed = LocalTime.parse(time);
			prencode = SHA.getprencode();
			upd = SQLrequest.insertUserBooking(SHA.getprencode(), dateparsed, timeparsed, cat, spec, pat, cf, 
												name, surname, residence, street, phone);
			if(upd == 0)
				prencode = null;
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
		return prencode;
	}
}
