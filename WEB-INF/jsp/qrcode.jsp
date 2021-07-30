<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="modalqr" class="modal">
	<form class="modal-content">
		<div class="container">
			<span class="close"><a href="<%=request.getContextPath()%>/CentroVaccinale/index.jsp">×</a></span>
			<h1>Riepilogo Prenotazione</h1>
			
			<h3>Codice fiscale: </h3>
			<h4 id="cf-riepilogo"></h4>
			
		    <h3>Data vaccinazione: </h3>
		    <h4 id="data-riepilogo"></h4>
		    
		    <h3>Ora vaccinazione: </h3>
		    <h4 id="ora-riepilogo"></h4>
		    
		    <h3>Codice prenotazione: </h3>
		    <h4 id="prencode-riepilogo"></h4>
		    
		    <br>
		    <div id="qrcode"></div>

    		<div class="clearfix">
				<a href="<%=request.getContextPath()%>/CentroVaccinale/index.jsp"><button type="button" class="cancelbtn">Chiudi</button></a>
			</div>
		</div>
	</form>
</div>