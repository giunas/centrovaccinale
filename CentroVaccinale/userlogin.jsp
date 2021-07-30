<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/842c252171.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="./css/form.css"/>
<link rel="stylesheet" href="./css/nav.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>
<script src="./js/bookingmanagement.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/navbar.jsp"%>

	<!-- Accesso -->
	<form id="vaccineform" action="../loginuser" method="POST">
		<div id="logintab" class="tab">
		    <h1>Login utenti</h1>
		    
			<h4>Codice fiscale</h4>
		    <p><input name="cf" id="cf" type="text" required="required"></p>
		    <h4>Codice prenotazione</h4>
		    <p><input name="prencode" id="prencode" type="text" required="required"></p>
	    </div>
	
		<div style="overflow:auto;">
			<div style="float:right;">
				<button type="submit" id="login">Accedi</button>
			</div>
		</div>
	</form>
</body>
</html>