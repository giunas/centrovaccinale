<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String active = request.getSession().getAttribute("active").toString();
	Object objact = request.getSession().getAttribute("active");
	Object objlog = session.getAttribute("logged");
	String active = "";
	boolean logged = false;
	
	if(objact != null)
		active = objact.toString();
	if(objlog != null)
		logged = (boolean) objlog;
	
	System.out.println(active + " " + logged);
	String basepath = request.getContextPath();
%>
<div class="navbar">
	<a id="logout" href="<%=basepath%>/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
	<a id="reserved" href="<%=basepath%>/CentroVaccinale/reservedlogin.jsp"><i class="fas fa-user-md"></i> Area riservata</a>
	<a id="bookman" href="<%=basepath%>/CentroVaccinale/userlogin.jsp"><i class="fa fa-calendar"></i> Gestione prenotazioni</a>
	<a id="book" href="<%=basepath%>/CentroVaccinale/bookvaccine.jsp"><i class="fas fa-syringe"></i> Prenota vaccino</a>
	<a id="portale" href="<%=basepath%>/CentroVaccinale/index.jsp"><i class="fas fa-viruses"></i> Portale vaccini</a>
</div>

<script>
$(document).ready(function(){
	$("#logout").hide();
	var path = window.location.pathname;
	
	<%if(logged == true) {%>
		$("#logout").show();	
		<%if(active == "booking") {%>
			$("#reserved").hide();
			$("#book").hide();
			$("#bookman").addClass("active");
		<%}%>
		<%if(active == "reserved") {%>
			$("#bookman").hide();
			$("#book").hide();
			$("#reserved").addClass("active");
		<%}%>
	<%}%>
	
	if(path == "/nasoproject/CentroVaccinale/reservedlogin.jsp")
		$("#reserved").addClass("active");
	else if(path == "/nasoproject/CentroVaccinale/userlogin.jsp")
		$("#bookman").addClass("active");
	else if(path == "/nasoproject/CentroVaccinale/bookvaccine.jsp")
		$("#book").addClass("active");
	else if(path == "/nasoproject/CentroVaccinale/index.jsp")
		$("#portale").addClass("active");	
});
</script>