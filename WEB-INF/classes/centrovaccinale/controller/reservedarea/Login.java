package centrovaccinale.controller.reservedarea;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.model.Account;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "LoginReserved", urlPatterns = { "/loginreserved" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Account account = new Account();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email != null && email != "" && password != null && password != "") {
			account.setEmail(email.trim());
			account.setPassword(password.trim());
			int res = account.login();
			
			if(res == 0 || res == 1) {
				request.getSession().setAttribute("email", account.getEmail());
				request.getSession().setAttribute("type", res);
				request.getSession().setAttribute("logged", true);
				request.getSession().setAttribute("active", "reserved");
				
				if(res == 0)
					response.sendRedirect(request.getContextPath() + "/doctorsearch");
				else
					response.sendRedirect(request.getContextPath() + "/officersearch");
					
			}
			else {
				request.getSession().setAttribute("logged", false);
				response.sendRedirect(request.getContextPath()+"/CentroVaccinale/reservedlogin.jsp");
			}
		}
			
		
		/*
		SHA.setpsw(password);
		
		List<List<String>> result = SQLrequest.searchReservedAccount(email, SHA.getpsw());
		if(result.get(1).contains(Integer.toString(0))) {
			// Medico se tipo = 0
			request.getSession().setAttribute("result", result);
			response.sendRedirect(request.getContextPath() + "/doctorsearch");
		}
		else if(result.get(1).contains(Integer.toString(1))) {
			// Funzionario se tipo = 1
			request.getSession().setAttribute("result", result);
			response.sendRedirect(request.getContextPath() + "/officersearch");
		}
		else {
			System.out.println("Errore");
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
