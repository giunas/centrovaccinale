package centrovaccinale.controller.reservedarea;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import centrovaccinale.model.Vaccines;

/**
 * Servlet implementation class LoadVaccine
 */
@WebServlet("/loadvaccine")
public class LoadVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Vaccines vaccines = new Vaccines();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadVaccine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String prencode = request.getParameter("prencode");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String vaccine = request.getParameter("vaccine");
		String reason = request.getParameter("reason");
		
		/*Date dateparsed = null;
		LocalTime timeparsed = null;
		
		try {
			dateparsed = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			timeparsed = LocalTime.parse(time);
			out.println(SQLrequest.insertVaccine(prencode, email, dateparsed, timeparsed, vaccine, reason));
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}*/
		
		if(prencode != null && prencode != "" && email != null && email != "" &&
			date != null && date != "" && time != null && time != "" && vaccine != null &&
			vaccine != "" && reason != null && reason != "") {
			vaccines.setPrencode(prencode.trim());
			vaccines.setEmail(email.trim());
			vaccines.setDate(date.trim());
			vaccines.setTime(time.trim());
			vaccines.setVaccine(vaccine.trim());
			vaccines.setReason(reason.trim());
			out.print(vaccines.insertlist());
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
