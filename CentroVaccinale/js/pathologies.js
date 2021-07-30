var file;
$(document).ready(function() {
	$("#cat").attr("required", "required");
	$("#spec").attr("required", "required");
	$("#pat").attr("required", "required");
	
	loadXMLDoc("./xml/pathologies.xml").done(function(file) {
		$("#spec").prop( "disabled", true );
		$("#pat").prop( "disabled", true );
		showResult("/categories/category/name", file, "cat", 0);
		
		$('#cat').change(function() { 
			//file = loadXMLDoc("pathologies.xml");
		    var value = $(this).val();				//cat1, oppure cat2, ect
		    //console.log(value);
		    showResult("//category[@id='"+value+"']/specifications/specification/name", file, "spec", value);
		});
		
		$('#spec').change(function() { 
			//file = loadXMLDoc("pathologies.xml");
		    var value = $(this).val();				//cat1spec1, oppure cat2spec2, ect
		    showResult("//specification[@id='"+value+"']/pathologies/pathology", file, "pat", 2);
		});
	})
});

function loadXMLDoc(file) {
	return $.get(file);
}

function showResult(path, xml, id_dest, type) {
	//console.log("entro")
    var txt = "";
    if (xml.evaluate) {
        var nodes = xml.evaluate(path, xml, null, XPathResult.ANY_TYPE, null);
        var result = nodes.iterateNext();
		//console.log(result);
        var i = 1;
		if(result != null) {
			if(type == 0) {
				$("#spec").prop( "disabled", true );
				$("#pat").prop( "disabled", true );
			}
			else if(type != 0 && type != 2) {
				$("#spec").prop( "disabled", false );
				$("#pat").html("");
				$("#pat").prop( "disabled", true );
			}
			else if(type == 2)
				$("#pat").prop( "disabled", false );
		}
		
		//Si verifica solo se una specification non contiene pathology
		if(result == null) {
			$("#pat").prop( "disabled", true );
			txt="<option value='' disabled='disabled' selected='selected'>Nessuna scelta</option>";
		}
        while (result) {
        	if(type == 0) {
				if(i == 1)
					txt+="<option value='' disabled='disabled' selected='selected'>Seleziona la categoria</option>";
            	txt += "<option value=cat"+i+">"+result.childNodes[0].nodeValue+"</option>";
        	}
            else if(type == 2) {
				if(i == 1)
					txt+="<option value='' disabled='disabled' selected='selected'>Seleziona la patologia</option>";
            	txt += "<option value="+i+">"+result.childNodes[0].nodeValue+"</option>";
            }
            else {
				if(i == 1)
					txt+="<option value='' disabled='disabled' selected='selected'>Seleziona la specifica</option>";
				txt += "<option value="+type+"spec"+i+">"+result.childNodes[0].nodeValue+"</option>";	
            }  	
            ++i;
            result = nodes.iterateNext();
        } 
    }

	document.getElementById(id_dest).innerHTML = txt;
}