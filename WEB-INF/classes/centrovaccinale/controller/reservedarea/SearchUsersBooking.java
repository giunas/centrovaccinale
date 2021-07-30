package centrovaccinale.controller.reservedarea;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.conversion.Convert;
import centrovaccinale.model.Bookings;

/**
 * Servlet implementation class Loadresult
 */
@WebServlet("/searchusersbooking")
public class SearchUsersBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Bookings bookings = new Bookings();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUsersBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String prencode = request.getParameter("prencode");
		
		/*Date dateparsed = null;
		LocalTime timeparsed = null;

		try {
			if(date != null || date != "") {
				dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				if(time != null && time != "" && prencode != null && prencode != "") {
					timeparsed = LocalTime.parse(time);
					request.setAttribute("bookingdata", SQLrequest.searchUsersBookingComplete(dateparsed, timeparsed, prencode));
				}
				else if(time != null && time != "") {
					timeparsed = LocalTime.parse(time);
					request.setAttribute("bookingdata", SQLrequest.searchUsersBookingByTimeAndDate(dateparsed, timeparsed));
				}
				else if(prencode != null && prencode != "")
					request.setAttribute("bookingdata", SQLrequest.searchUsersBookingByPrencodeAndDate(dateparsed, prencode));
				else {
					//request.setAttribute("bookingdata", SQLrequest.searchUsersBookingByDate(dateparsed));
					//System.out.println(Convert.toJSON(SQLrequest.searchUsersBookingByDate(dateparsed)));
					out.print(Convert.toJSON(SQLrequest.searchUsersBookingByDate(dateparsed)));
				}
				//out.print("OK");
			}
		} 
		catch (ParseException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}*/
		
		if(date != null && date != "") {
			bookings.setDate(date.trim());
			if(time != null && time != "" && prencode != null && prencode != "") {
				bookings.setTime(time.trim());
				bookings.setPrencode(prencode.trim());
				out.print(Convert.toJSON(bookings.searchComplete()));
				//request.setAttribute("bookingdata", bookings.searchComplete());
			}
			else if(time != null && time != "") {
				bookings.setTime(time.trim());
				out.print(Convert.toJSON(bookings.searchByTimeAndDate()));
				//request.setAttribute("bookingdata", bookings.searchByTimeAndDate());
			}
			else if(prencode != null && prencode != "") {
				bookings.setPrencode(prencode.trim());
				out.print(Convert.toJSON(bookings.searchByPrencodeAndDate()));
				//request.setAttribute("bookingdata", bookings.searchByPrencodeAndDate());
			}
			else {
				out.print(Convert.toJSON(bookings.searchByDate()));
				//request.setAttribute("bookingdata", bookings.searchByDate());
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
