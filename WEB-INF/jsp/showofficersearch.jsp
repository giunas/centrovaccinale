<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/842c252171.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/nav.css"/>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/form.css"/>
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/table.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.0/FileSaver.min.js"></script>
</head>
<%
	//@SuppressWarnings("unchecked")
	//List<List<String>> result = (List<List<String>>)request.getSession().getAttribute("result");
	String email = request.getSession().getAttribute("email").toString();
%>
<body>
<%@ include file="navbar.jsp"%>

<!-- Vista medico per caricamento esiti vaccinazione -->
<!-- Ricerca con codice prenotazione -->
<form id="vaccineform">
	<div id="searchtab" class="tab">
		<h1>Cerca vaccinazioni</h1>
		<h3>Inserisci il range di date per ottenere il report</h3>

		<div class="tab">
			<h4>Data inizio</h4>
			<p><input name="startdate" id="startdate" type="date" required></p>
			<h4>Data fine</h4>
			<p><input name="finishdate" id="finishdate" type="date" required></p>
		</div>		
		
		<div style="overflow:auto;">
			<div style="float:right;">
				<button type="button" id="search"><i class="fas fa-search"></i></button>
			</div>
		</div>
	</div>
</form>

<button type="button" id="download">Download report XML <i class="fas fa-file-download"></i></button>
<br><br>
<div id="result"></div>

<script>
var email = "<%=email%>";
//result.get(1).get(0)
var xml;

$(document).ready(function() {
	$(".tab").animate({
		width: "toggle"
	});
	
	$("#download").hide();
	
	$("#search").click(function() {
		$.post("searchreport", {
			startdate : $("#startdate").val(),
			finishdate : $("#finishdate").val()
		}, function(data, status) {
			if (status == "success" && data != null) {
				xml = data;
				createTable();
			}
		});
	});	
});

function createTable() {
	$("#download").show();
	
	$("#result").html("");
	$("#result").append("<table id='xml'><tr><th>Codice fiscale</th><th>Data vaccinazione</th><th>Ora vaccinazione</th><th>Vaccino</th><th>Email medico</th></tr></table>")
	$(xml).find("Vaccinazione").each(function() {
		$("#xml").append("<tr><td>"+$(this).find("cf").text()+"</td><td>"+$(this).find("data_eff").text()+"</td><td>"+$(this).find("ora_eff").text()+"</td><td>"+$(this).find("vaccino").text()+"</td><td>"+$(this).find("email_medico_fk").text()+"</td></tr>");
	});  
	
	appendEventClickDownload();
}

function appendEventClickDownload() {
	$("#download").click(function() {	
		var blob = new Blob([(new XMLSerializer).serializeToString(xml)], {type: "application/xml"});
		saveAs(blob, "report.xml");
	});
}
</script>

</body>
</html>