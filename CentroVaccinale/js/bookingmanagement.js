$(document).ready(function() {
	$(".tab").animate({
		width: "toggle"
	});

	$("#login").css("display", "inline");
	
	/*$("#login").click(function() {
		if ($("#vaccineform")[0].checkValidity()) {
			$.post("../../loginuser", {
				cf : $("#cf").val(),
				prencode : $("#prencode").val()				
			}, function(data, status) {
				if (status == "success")
					$("body").html(data);
			});
		}
		else {
			$("#vaccineform")[0].reportValidity();
		}
	});*/
});