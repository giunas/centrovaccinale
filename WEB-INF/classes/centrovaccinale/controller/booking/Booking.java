package centrovaccinale.controller.booking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.model.BookingBean;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BookingBean booking = new BookingBean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String cat = request.getParameter("cat");
		String spec = request.getParameter("spec");
		String pat = request.getParameter("pat");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String cf = request.getParameter("cf");
		String residence = request.getParameter("residence");
		String street = request.getParameter("street");
		String phone = request.getParameter("phone");
		
		// L'omocodia in prencode è rara, soprattutto perchè data e ora fanno da discriminante. 
		// Nel caso in cui si provi ad effettuare una prenotazione con stessa data e ora,
		// l'INSERT nella tabella lancerebbe un'eccezione. (DA GESTIRE)
		/*SHA.setprencode(cf+date+time);
		String prencode = SHA.getprencode();
		
		Date dateparsed = null;
		LocalTime timeparsed = null;
		
		try {
			dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			timeparsed = LocalTime.parse(time);
			out.println("Count: " + SQLrequest.insertUserBooking(prencode, dateparsed, timeparsed, cat, spec, pat, cf, name, surname,
													residence, street, phone) + " Prencode: " + prencode);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}*/
		
		if(cat != null && cat != "" && spec != null && spec != "" && pat != null && pat != "" &&
			date != null && date != "" && time != null && time != "" && name != null && name != "" &&
			surname != null && surname != "" && cf != null && cf != "" && residence != null && residence != "" &&
			street != null && street != "" && phone != null && phone != "") {
			
			String prencode = booking.insertlist(date.trim(), time.trim(), cat.trim(), spec.trim(), 
												pat.trim(), cf.trim(), name.trim(), surname.trim(), 
												residence.trim(), street.trim(), phone.trim());
			if(prencode == null)
				out.print("ERR");
			else
				out.print(prencode);
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
