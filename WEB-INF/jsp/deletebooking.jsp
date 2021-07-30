<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="modaldelete" class="modal">
	<form class="modal-content">
		<div class="container">
			<span onclick="document.getElementById('modaldelete').style.display='none'" class="close">×</span>
			<h1>Elimina Prenotazione</h1>
			<h3>Dopo l'eliminazione verrai rimandato alla pagina principale.</h3>
			<h3>Sei sicuro di voler eliminare la tua prenotazione ?</h3>
    
    		<div class="clearfix">
				<button type="button" onclick="document.getElementById('modaldelete').style.display='none'" class="cancelbtn">Annulla</button>
				<button type="button" class="deletebtn">Elimina</button>
			</div>
		</div>
	</form>
</div>