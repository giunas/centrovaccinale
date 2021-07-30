var currentTab; // Current tab is set to be the first tab (0)
var numTab = 3;

$(document).ready(function(){
	currentTab = 0;
	showTab(currentTab); // Display the current tab
	
	setDateTime();
	
	$("#date").attr("min", getDate());
	$("#time").attr("min", "08:00");
	$("#time").attr("max", "00:00");
});

function showTab(n) {
	$(".tab#"+n).animate({
		width: "toggle"
	});
	
	//$(".tab#"+n).fadeIn();
	
	if(n == 0)
		$("#prevBtn").css("display", "none");
	else
		$("#prevBtn").css("display", "inline");
	if(n == 2)
		$("#nextBtn").html("Conferma");	
	else
		$("#nextBtn").html("Avanti");
	
	fixStepIndicator(n);
}

function nextPrev(n) {
	// Exit the function if any field in the current tab is invalid:
	if (n == 1 && !validateForm()) return false;
	// Hide the current tab:
	$(".tab#"+currentTab).hide();
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form...
	if (currentTab >= numTab) {
		// ... the form gets submitted:
		conferma();
		return false;
	}
	// Otherwise, display the correct tab:
	showTab(currentTab);
}

function fixStepIndicator(n) {
	// This function removes the "active" class of all steps...
	var i, x = document.getElementsByClassName("step");
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
	}
	//... and adds the "active" class on the current step:
	x[n].className += " active";
}

function validateForm() {
  // This function deals with validation of the form fields
  var x, y, i, valid = true;
  x = document.getElementsByClassName("tab");
  y = x[currentTab].getElementsByTagName("input");
  
  z = x[currentTab].getElementsByTagName("select");

  for(i = 0; i < z.length; i++) {
  	if(z[i].checkValidity() == false) {
		z[i].reportValidity();
		valid = false;
		return;
	}
  }

  for(i = 0; i < y.length; i++) {
  	if(y[i].checkValidity() == false) {
		y[i].reportValidity();
		valid = false;
		return;
	}
  }

  if(valid) {
	document.getElementsByClassName("step")[currentTab].className += " finish";
  }

  return valid;
}

function conferma() {
	var cf = $("#cf").val();
	var prencode;
	var date = $("#date").val();
	var time = $("#time").val();
	$.post("../booking", 
		{
			cat : $("#cat option:selected").text(),
			spec : $("#spec option:selected").text(),
			pat : $("#pat option:selected").text(),
			date : date,
			time : time,
			name : $("#name").val(),
			surname : $("#surname").val(),
			cf : cf,
			residence : $("#residence").val(),
			street : $("#street").val(),
			phone : $("#phone").val()			
		}, function(data, status) {
			if (status == "success" && data != null) {
				prencode = data;
				qrcode(cf, prencode, date, time);
			}
			else
				alert("Errore");
	});
}

function qrcode(cf, prencode, date, time) {
	$("#cf-riepilogo").html(cf);
	$("#data-riepilogo").html(date);
	$("#ora-riepilogo").html(time);
	$("#prencode-riepilogo").html(prencode);
	$("#qrcode").qrcode(prencode);
	$("#modalqr").css("display", "block");
	$("body").addClass('showing-modal');
	//$("body").removeClass('showing-modal');
}


