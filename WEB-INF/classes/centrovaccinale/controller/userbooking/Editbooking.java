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
 * Servlet implementation class Editbooking
 */
@WebServlet("/editbooking")
public class Editbooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editbooking() {
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
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String phone = request.getParameter("phone");

		/*
		try {
			dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			timeparsed = LocalTime.parse(time);
			//Query q = Sqlreq.editUserBooking(cf, prencode, dateparsed, timeparsed, phone);
			//int result = q.getUpdateCount();
			//out.println(result);
			out.println(SQLrequest.editUserBooking(cf, prencode, dateparsed, timeparsed, phone));
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		*/
		
		if(cf != null && cf != "" && prencode != null && prencode != "" &&
			date != null && date != "" && time != null && time != "" &&
			phone != null && phone != "") {
			int res = booking.editlist(cf.trim(), prencode.trim(), date.trim(), 
										time.trim(), phone.trim());
			out.println(res);
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
