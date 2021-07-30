package centrovaccinale.controller.userbooking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.model.BookingBean;

/**
 * Servlet implementation class Deletebooking
 */
@WebServlet("/deletebooking")
public class Deletebooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletebooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		BookingBean booking = (BookingBean) request.getSession().getAttribute("bookingBean");
		
		String cf = request.getParameter("cf");
		String prencode = request.getParameter("prencode");
		
		//out.println(SQLrequest.deleteUserBooking(cf, prencode));
		
		if(cf != null && cf != "" && prencode != null && prencode != "") {
			int res = booking.deletelist(cf.trim(), prencode.trim());
			if(res == 1) {
				request.getSession().setAttribute("logged", false);
				request.getSession().removeAttribute("bookingBean");
				out.print(res);
				//response.sendRedirect(request.getContextPath() + "/logout");
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
