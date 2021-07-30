package centrovaccinale.controller.userbooking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.model.BookingBean;

/**
 * Servlet implementation class Showbooking
 */
@WebServlet("/loginuser")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookingBean booking = new BookingBean();
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
		String cf = request.getParameter("cf");
		String prencode = request.getParameter("prencode");
	
		if(cf != null && cf != "" && prencode != null && prencode != "") {
			booking.setCF(cf.trim());
			booking.setPrencode(prencode.trim());
			int res = booking.login();
			
			if(res > 1) {
				//request.getSession().setAttribute("result", booking.getResult());
				request.getSession().setAttribute("bookingBean", booking);
				request.getSession().setAttribute("logged", true);
				request.getSession().setAttribute("active", "booking");
				response.sendRedirect(request.getContextPath() + "/showbooking");
			}
			else {
				request.getSession().setAttribute("logged", false);
				response.sendRedirect(request.getContextPath()+"/CentroVaccinale/userlogin.jsp");
			}
		}

		/*
		SHA.setprencode(prencode);
		
		request.getSession().setAttribute("result", SQLrequest.searchUserBooking(cf, SHA.getprencode()));
		response.sendRedirect(request.getContextPath() + "/showbooking");
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
