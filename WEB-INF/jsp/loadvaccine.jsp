<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>

<div id="modal" class="modal">
	<form class="modal-content">
		<div class="container">
			<span class="close">×</span>
			<h1>Carica esito</h1>

			<div class="tab">
				<h4>Codice prenotazione</h4>
			    <p><input id="prencode" type="text" required></p>
			    <h4>Data effettiva</h4>
			    <p><input id="truedate" type="date" required></p>
			    <h4>Ora effettiva</h4>
			    <p><input id="truetime" type="time" required pattern="[0-9]{2}:[0-9]{2}"></p>
			    <h4>Vaccino</h4>
	    		<select id="vaccine" required>
	    			<option value='' disabled='disabled' selected='selected'>Seleziona vaccino</option>
	    			<option value="Rifiutato">Rifiutato</option>
	    			<option value="Pfizer">Pfizer</option>
	    			<option value="Moderna">Moderna</option>
	    			<option value="Astrazeneca">Astrazeneca</option>
	    			<option value="JJ">Johnson &amp; Johnson</option>
	    		</select>
	    		<h4>Motivo del rifiuto</h4>
	    		<textarea id="reason" rows="4" cols="50" required></textarea>
		    </div>
  
    		<div class="clearfix">
				<button type="button" class="cancelbtn">Annulla</button>
				<button type="button" class="editbtn">Carica</button>
			</div>
		</div>
	</form>
</div>
