<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ page import="java.util.List" %>
<%@ page import="centrovaccinale.model.BookingBean" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/842c252171.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/nav.css"/>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/form.css"/>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/modal.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>
<script src="CentroVaccinale/js/dateandtime.js"></script>
</head>
<%
//List<List<String>> result = (List<List<String>>)request.getSession().getAttribute("result");
	BookingBean booking = (BookingBean) request.getSession().getAttribute("bookingBean");
	List<List<String>> result = (List<List<String>>) booking.getResult();
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date dateparsed = formatter.parse(result.get(1).get(1));
	String todaydate = formatter.format(new Date());
	Date todaydateparsed = formatter.parse(todaydate);
%>
<body>

<%@ include file="navbar.jsp"%>
<%@ include file="deletebooking.jsp"%>
<%@ include file="editbooking.jsp"%>

<!-- Modifica/Cancella prenotazione -->
<div id="bookings">
		<%
		// the data
		for (int r = 1; r < result.size(); r++) {%>
			<div id="<%=r%>" class="cards">
				<h3>Prenotazione</h3>
				<br>
				
		<%	for (int i = 0; i < result.get(0).size()-1; i++) {%>
				
				<p><b id="col<%=i%>"></b>: <span id="<%=result.get(0).get(i)%>"><%=result.get(r).get(i)%></span></p>
				
		<%	}%>
			
			<%if(result.get(r).get(12).equals("0")) {%>
			<%if(dateparsed.after(todaydateparsed)) {%>
			<div style="overflow:auto;">
				<div style="float:right;">
					<button type="button" onclick="document.getElementById('modaldelete').style.display='block'">Cancella prenotazione</button>
					<button type="button" onclick="document.getElementById('modaledit').style.display='block'">Modifica prenotazione</button>
				</div>
			</div>
			<%}%>
			<%}%>
		</div>
	<%	}%>
</div>

<script>
$(document).ready(function() {
	$(".tab").animate({
		width: "toggle"
	});
	
	setDateTime();	
	$("#date").attr("min", getDate());
	$("#time").attr("min", "08:00");
	$("#time").attr("max", "00:00");
	
	var column = ["Codice di prenotazione", "Data prenotazione", "Ora prenotazione",
					"Categoria", "Specifica", "Patologia", "Codice fiscale", "Nome",
					"Cognome", "Comune", "Indirizzo", "Telefono"];
	for(var i=0; i<12; i++)
		$("#col"+i).html(column[i]);
	
	$("#date").val($("#data").html());
	$("#time").val($("#ora").html());
	$("#phone").val($("#telefono").html());
	
	$(".deletebtn").click(function() {
		$.post("deletebooking", {
			prencode : $("#cod_pren").html(),
			cf : $("#cf").html()
		}, function(data, status) {
			if (status == "success" && data == 1) {
				window.location.replace("CentroVaccinale/index.jsp");
			}
		});
	});	
	
	$(".editbtn").click(function() {
		if ($("#modal-content-edit")[0].checkValidity()) {
			var date = $("#date").val();
			var time = $("#time").val();
			var phone = $("#phone").val();
			$.post("editbooking", {
				prencode : $("#cod_pren").html(),
				cf : $("#cf").html(),
				date : date,
				time : time,
				phone : phone			
			}, function(data, status) {
				if (status == "success" && data == 1) {
					document.getElementById('modaledit').style.display='none';
					$("#data").html(date);
					$("#ora").html(time);
					$("#telefono").html(phone);
				}
			});
		}
		else {
			$("#modal-content-edit")[0].reportValidity();
		}
	});
	
});
</script>

</body>
</html>