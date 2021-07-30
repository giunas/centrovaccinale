package centrovaccinale.controller.sql;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class SQLrequest {
	public static List<List<String>> searchUserBooking(String cf, String cod_pren) {
		String rawquery = "SELECT * FROM prenotazione WHERE cod_pren=? AND cf=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, cod_pren);
			SQLhelp.getStatement().setString(2, cf);
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static List<List<String>> searchUserVaccine(String cod_pren) {
		String rawquery = "SELECT * FROM vaccinazione WHERE cod_pren_fk=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, cod_pren);
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static int deleteUserBooking(String cf, String cod_pren) {
		String rawquery = "DELETE FROM prenotazione WHERE cod_pren=? AND cf=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, cod_pren);
			SQLhelp.getStatement().setString(2, cf);
			SQLhelp.upd();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getCount();
	}
	
	public static int editUserBooking(String cf, String cod_pren, Date date, LocalTime time, String phone) {
		String rawquery = "UPDATE prenotazione SET data=?, ora=?, telefono=? WHERE cod_pren=? AND cf=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setDate(1, new java.sql.Date(date.getTime()));
			SQLhelp.getStatement().setTime(2, Time.valueOf(time));
			SQLhelp.getStatement().setString(3, phone);
			SQLhelp.getStatement().setString(4, cod_pren);
			SQLhelp.getStatement().setString(5, cf);
			SQLhelp.upd();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getCount();	
	}
	
	public static int insertUserBooking(String prencode, Date date, LocalTime time, String cat, String spec, String pat,
	String cf, String name, String surname, String residence, String street, String phone) {
		String rawquery = "INSERT IGNORE INTO prenotazione (cod_pren, data, ora, categoria, specifica, patologia, "
												+ "cf, nome, cognome, comune, indirizzo, telefono) "
												+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, prencode);
			SQLhelp.getStatement().setDate(2, new java.sql.Date(date.getTime()));
			SQLhelp.getStatement().setTime(3, Time.valueOf(time));
			SQLhelp.getStatement().setString(4, cat);
			SQLhelp.getStatement().setString(5, spec);
			SQLhelp.getStatement().setString(6, pat);
			SQLhelp.getStatement().setString(7, cf);
			SQLhelp.getStatement().setString(8, name);
			SQLhelp.getStatement().setString(9, surname);
			SQLhelp.getStatement().setString(10, residence);
			SQLhelp.getStatement().setString(11, street);
			SQLhelp.getStatement().setString(12, phone);
			SQLhelp.upd();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getCount();		
	}
	
	public static List<List<String>> searchReservedAccount(String email, String password) {
		String rawquery = "SELECT * FROM account WHERE email=? AND password=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, email);
			SQLhelp.getStatement().setString(2, password);
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static List<List<String>> searchUsersBookingComplete(Date date, LocalTime time, String cod_pren) {
		String rawquery = "SELECT * FROM prenotazione WHERE cod_pren=? AND data=? AND ora=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, cod_pren);
			SQLhelp.getStatement().setDate(2, new java.sql.Date(date.getTime()));
			SQLhelp.getStatement().setTime(3, Time.valueOf(time));
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static List<List<String>> searchUsersBookingByTimeAndDate(Date date, LocalTime time) {
		String rawquery = "SELECT * FROM prenotazione WHERE data=? AND ora=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setDate(1, new java.sql.Date(date.getTime()));
			SQLhelp.getStatement().setTime(2, Time.valueOf(time));
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static List<List<String>> searchUsersBookingByPrencodeAndDate(Date date, String cod_pren) {
		String rawquery = "SELECT * FROM prenotazione WHERE cod_pren=? AND data=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setString(1, cod_pren);
			SQLhelp.getStatement().setDate(2, new java.sql.Date(date.getTime()));
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static List<List<String>> searchUsersBookingByDate(Date date) {
		String rawquery = "SELECT * FROM prenotazione WHERE data=?";
		
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setDate(1, new java.sql.Date(date.getTime()));
			SQLhelp.exec();
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getResult();
	}
	
	public static int insertVaccine(String prencode, String emaildoc, Date truedate, LocalTime truetime, String vaccine,
	String reason) {
		String rawquery = "INSERT INTO vaccinazione (data_eff, ora_eff, vaccino, motivo, cod_pren_fk, email_medico_fk) "
												+ "VALUES (?,?,?,?,?,?)";
		
		try {
			SQLhelp.open();
			
			SQLhelp.getConnection().setAutoCommit(false);
			
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setDate(1, new java.sql.Date(truedate.getTime()));
			SQLhelp.getStatement().setTime(2, Time.valueOf(truetime));
			SQLhelp.getStatement().setString(3, vaccine);
			SQLhelp.getStatement().setString(4, reason);
			SQLhelp.getStatement().setString(5, prencode);
			SQLhelp.getStatement().setString(6, emaildoc);
			SQLhelp.upd();
			
			if(SQLhelp.getCount() == 1) {
				System.out.println("Entro nel secondo blocco");
				int vaccinato = 1;
				String rawquery2 = "UPDATE prenotazione SET stato = ? WHERE cod_pren = ?";
				SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery2));
				SQLhelp.getStatement().setInt(1, vaccinato);
				SQLhelp.getStatement().setString(2, prencode);
				SQLhelp.upd();
				SQLhelp.getConnection().commit();
			}
			else {
				System.out.println("ROLLBACK");
				SQLhelp.getConnection().rollback();
			}
			
			SQLhelp.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getCount();		
	}
	
	public static String searchVaccines(Date startdate, Date finishdate) {
		String rawquery = "SELECT p.cf, v.data_eff, v.ora_eff, v.vaccino, v.email_medico_fk "
							+ "FROM vaccinazione v, prenotazione p "
							+ "WHERE v.cod_pren_fk=p.cod_pren AND data_eff >= ? AND data_eff <= ?";
		try {
			SQLhelp.open();
			SQLhelp.setStatement(SQLhelp.getConnection().prepareStatement(rawquery));
			SQLhelp.getStatement().setDate(1, new java.sql.Date(startdate.getTime()));
			SQLhelp.getStatement().setDate(2, new java.sql.Date(finishdate.getTime()));
			SQLhelp.execWithDoc();
			
			SQLhelp.validateDoc();
			
			SQLhelp.close();			
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return SQLhelp.getDoc();
	}
	
}
