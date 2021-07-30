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
</head>
<body>
	<%@ include file="/WEB-INF/jsp/navbar.jsp"%>
	
	<form id="vaccineform" action="../loginreserved" method="POST">
		<div id="reservedtab" class="tab">
		    <h1>Area riservata</h1>
		    
		    <h4>Email</h4>
		    <p><input name="email" id="email" type="email" required></p>
		    <h4>Password</h4>
		    <p><input name="password" id="password" type="password" required></p>
	    </div>
	
		<div style="overflow:auto;">
			<div style="float:right;">
				<button type="submit">Accedi</button>
			</div>
		</div>
	</form>

<script>
$(".tab").animate({
	width: "toggle"
});

//$("#login").css("display", "inline");
</script>

</body>
</html>