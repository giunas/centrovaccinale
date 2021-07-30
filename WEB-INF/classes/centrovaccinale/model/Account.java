package centrovaccinale.model;

import java.io.Serializable;
import java.util.List;

import centrovaccinale.controller.sql.SQLrequest;
import centrovaccinale.security.SHA;

public class Account implements Serializable {
	private static final long serialVersionUID = -384501995973989636L;
	private String email;
	private String password;
	private int type;
	private boolean logged;
	
	public Account() {
		email = null;
		password = null;
		type = -1;
		logged = false;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		SHA.setpsw(password);
		this.password = SHA.getpsw();
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean getLogged() {
		return logged;
	}
	
	public int login() {
		List<List<String>> result = SQLrequest.searchReservedAccount(email, password);
		if(result.size() > 1) {
			
			if(result.get(1).contains(Integer.toString(0))) {
				// Medico se tipo = 0
				type = 0;
				logged = true;
			}
			else if(result.get(1).contains(Integer.toString(1))) {
				// Funzionario se tipo = 1
				type = 1;
				logged = true;
			}
		}
		else {
			email = null;
			password = null;
		}
		
		// Se non esiste l'account ritorna -1
		return type;
	}
}
