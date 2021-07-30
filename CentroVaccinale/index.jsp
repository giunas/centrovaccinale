<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
	<link rel="stylesheet" href="CentroVaccinale/css/home.css"/>
	<link rel="stylesheet" href="CentroVaccinale/css/nav.css"/>	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/fontawesome.min.css" integrity="sha512-OdEXQYCOldjqUEsuMKsZRj93Ht23QRlhIb8E/X0sbwZhme8eUw6g8q7AdxGJKakcBbv7+/PX0Gc2btf7Ru8cZA==" crossorigin="anonymous"/>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous"></script>

	<script src="CentroVaccinale/js/home.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="https://kit.fontawesome.com/842c252171.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/navbar.jsp"%>
	<div class="tab">
		<button class="tablink" onclick="openPage('Map', this, 'red')" id="defaultOpen"><i class="fas fa-globe-europe"></i></button>
		<button class="tablink" onclick="openPage('Category', this, 'green')"><i class="fas fa-chart-pie"></i></button>
		<button class="tablink" onclick="openPage('Cases', this, 'blue')"><i class="fas fa-chart-bar"></i></button>
	</div>
	
<!--  Mappa dosi somministrate/dosi consegnate/percentuale totali per regione (vaccini-summary-latest.json)-->
<!--  Statistiche dosi somministrate in base ad anagrafica totali per regione (anagrafica-vaccini-summary-latest.json)-->
<!--  Statistiche Covid del giorno con nuovi positivi/tamponi per regione con grafico a barre (dpc-covid19-ita-regioni-latest.json) -->
<!--  ??? -->

	<div id="Map" class="tabcontent">
		<h3>Mappa delle somministrazioni di vaccino per regione</h3>
		<div id="geochart-colors" style="width: 700px; height: 433px;"></div>
	</div>

	<div id="Category" class="tabcontent">
		<h3>Statistiche delle somministrazioni per categoria</h3>
		<div id="piechart_3d"></div>
	</div>

	<div id="Cases" class="tabcontent">
		<h3>Statistiche dei nuovi casi di Covid-19 per regione</h3>
		 <div id="columnchart_material"></div>
	</div>
</body>
</html> 