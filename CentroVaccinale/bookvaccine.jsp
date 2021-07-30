<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/842c252171.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" type="text/css" href="./css/modal2.css"/>
<link rel="stylesheet" href="./css/nav.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.qrcode/1.0/jquery.qrcode.min.js" integrity="sha512-NFUcDlm4V+a2sjPX7gREIXgCSFja9cHtKPOL1zj6QhnE0vcY695MODehqkaGYTLyL2wxe/wtr4Z49SvqXq12UQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="./js/bookvaccine.js"></script>
<script src="./js/pathologies.js"></script>
<script src="./js/dateandtime.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/navbar.jsp"%>

	<form id="vaccineform">
		<!-- One "tab" for each step in the form: -->
		<div id="0" class="tab">
		    <h1>Prenotazione</h1>
		    
		    <h4>Categoria</h4>
			<select id="cat" required></select>
	
			<h4>Specifica</h4>    
			<select id="spec" required></select>
			
			<h4>Patologia</h4>
			<select id="pat" required></select>
	
		    <h4>Data</h4>
		    <p><input id="date" type="date" required></p>
		    <h4>Orario</h4>
		    <p><input id="time" type="time" required pattern="[0-9]{2}:[0-9]{2}"></p>
	    </div>
	  
		<div id="1" class="tab">
		    <h1>Dati anagrafici</h1>
		    
		    <h4>Nome</h4>
		    <p><input id="name" type="text" required></p>
		   	<h4>Cognome</h4>
		    <p><input id="surname" type="text" required></p>
		    <h4>Codice fiscale</h4>
		    <p><input id="cf" type="text" required></p>
		    <h4>Comune di residenza</h4>
		    <p><input id="residence" type="text" required></p>
		    <h4>Via e civico</h4>
		    <p><input id="street" type="text" required></p>
		   	<h4>Telefono</h4>
		    <p><input id="phone" type="tel" required pattern="[0-9]{9}|[0-9]{10}"></p>
	    </div>
	    
		<div id="2" class="tab">
		    <h1>Conferma</h1>
		    
			<h3>Ricontrolla i dati inseriti qui sotto e clicca sul pulsante "Conferma" per confermare la prenotazione.</h3>
		</div>
		
		<div style="overflow:auto;">
			<div style="float:right;">
				<button type="button" id="prevBtn" onclick="nextPrev(-1)">Indietro</button>
				<button type="button" id="nextBtn" onclick="nextPrev(1)">Avanti</button>
			</div>
		</div>
		<!-- Circles which indicates the steps of the form: -->
		<div style="text-align:center;margin-top:40px;">
			<span class="step"></span>
			<span class="step"></span>
			<span class="step"></span>
		</div>
	</form>

	<%@ include file="/WEB-INF/jsp/qrcode.jsp" %>
</body>
</html>