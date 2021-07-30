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
<link rel="stylesheet" type="text/css" href="CentroVaccinale/css/modal2.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>
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
		<h1>Cerca prenotazione</h1>
		<h3>Compila uno o più campi per affinare la ricerca</h3>

		<div class="tab">
			<h4>Data</h4>
			<p><input name="date" id="date" type="date" required></p>
			<h4>Orario</h4>
			<p><input name="time" id="time" type="time" min="08:00" max="00:00" pattern="[0-9]{2}:[0-9]{2}"></p>
			<h4>Codice prenotazione</h4>
			<p><input name="prencode" id="prencode" type="text"></p>
		</div>		
		
		<div style="overflow:auto;">
			<div style="float:right;">
				<button type="button" id="search"><i class="fas fa-search"></i></button>
			</div>
		</div>
	</div>
</form>

<%@ include file="loadvaccine.jsp"%>

<div id="result"></div>

<script>
var selectedtr = "";
var email = "<%=email%>";
$(document).ready(function() {
	$(".tab").animate({
		width: "toggle"
	});
	
	$("#search").click(function() {
		if ($("#vaccineform")[0].checkValidity()) {
			$.post("searchusersbooking", {
				date : $("#date").val(),
				time : $("#time").val(),
				prencode : $("#prencode").val()
			}, function(data, status) {
				if (status == "success") {
					var parsedData = JSON.parse(data);
					console.log(parsedData);
					createTable(parsedData);
				}
			});
		}
		else 
			$("#vaccineform")[0].reportValidity();
	});	
});

function createTable(data) {
	var table = "";
	table += "<table>"+
				"<tr>"+
					"<!-- Mostrare pulsante esito solo se non confermato -->"+
					"<th>Esito</th>"+
					"<th>Codice prenotazione</th>"+
					"<th>Data scelta</th>"+
					"<th>Ora scelta</th>"+
					"<th>Categoria</th>"+
					"<th>Specifica</th>"+
					"<th>Patologia</th>"+
					"<th>Codice fiscale</th>"+
					"<th>Nome</th>"+
					"<th>Cognome</th>"+
					"<th>Comune di residenza</th>"+
					"<th>Via e civico</th>"+
					"<th>Telefono</th>"+
				"</tr>";
	for(var i=0; i<data.data.length; i++) {
		table += "<tr id="+i+">";
		
		var elem = data.data[i];
		if(elem.stato == 1)
			table += "<td><b>Caricato</b></td>";
		else
			table += "<td><button type='button'>Carica esito</button></td>";
		table += "<td id=pren"+i+">"+elem.cod_pren+"</td>"+
				"<td>"+elem.data+"</td>"+
				"<td>"+elem.ora+"</td>"+
				"<td>"+elem.categoria+"</td>"+
				"<td>"+elem.specifica+"</td>"+
				"<td>"+elem.patologia+"</td>"+
				"<td>"+elem.cf+"</td>"+
				"<td>"+elem.nome+"</td>"+
				"<td>"+elem.cognome+"</td>"+
				"<td>"+elem.comune+"</td>"+
				"<td>"+elem.indirizzo+"</td>"+
				"<td>"+elem.telefono+"</td>";
	}
	table +=
		"</tr>"+
		"</table>";
	$("#result").html(table);
	
	appendEventClickLoad();
	appendEventClickComplete();
	appendEventClickClose();
}

function appendEventClickLoad() {
	$("#result button").click(function() {
		selectedtr = $(this).closest('tr').attr('id');
		var prensel = "#pren"+selectedtr;
		$(".modal #prencode").val($(prensel).html());
		$("#modal").css("display","block");
		$("body").addClass('showing-modal');
	});
}

function appendEventClickComplete() {
	$(".editbtn").click(function () {
		if ($(".modal-content")[0].checkValidity()) {
			$.post("loadvaccine", {
				prencode : $(".modal #prencode").val(),
				email : email,
				date : $("#truedate").val(),
				time : $("#truetime").val(),
				vaccine : $("#vaccine").val(),
				reason : $("#reason").val()		
			}, function(data, status) {
				if (status == "success" && data == 1) {
					$("body").removeClass('showing-modal');
					$("#modal").hide();
					
					var stringselected = "#"+selectedtr;
					$(stringselected+" button").remove();
					$(stringselected+" td:eq(0)").html("<b>Caricato</b>");
				}
			});
		}
		else
			$(".modal-content")[0].reportValidity();
	});
}

function appendEventClickClose() {
	$(".cancelbtn").click(function() {
		$("body").removeClass('showing-modal');
		$("#modal").hide();
	});
	
	$(".close").click(function() {
		$("body").removeClass('showing-modal');
		$("#modal").hide();
	});	
}
</script>

</body>
</html>