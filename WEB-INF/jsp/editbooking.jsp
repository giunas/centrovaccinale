<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Modifica tramite codice prenotazione -->
<!-- Dati modificabili: data, orario, telefono -->
<div id="modaledit" class="modal">
	<form id="modal-content-edit" class="modal-content">
		<div class="container">
			<span onclick="document.getElementById('modaledit').style.display='none'" class="close">×</span>
			<h1>Modifica Prenotazione</h1>

			<div class="tab">
			    <h4>Data</h4>
			    <p><input id="date" type="date" required></p>
			    <h4>Orario</h4>
			    <p><input id="time" type="time" required pattern="[0-9]{2}:[0-9]{2}"></p>
				<h4>Telefono</h4>
			    <p><input id="phone" type="tel" minlength="9" maxlength="10" required></p>
		    </div>
  
    		<div class="clearfix">
				<button type="button" onclick="document.getElementById('modaledit').style.display='none'" class="cancelbtn">Annulla</button>
				<button type="button" class="editbtn">Modifica</button>
			</div>
		</div>
	</form>
</div>