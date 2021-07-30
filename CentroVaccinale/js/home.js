var url = ["https://raw.githubusercontent.com/italia/covid19-opendata-vaccini/master/dati/vaccini-summary-latest.json",
			"https://raw.githubusercontent.com/italia/covid19-opendata-vaccini/master/dati/anagrafica-vaccini-summary-latest.json",
			"https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni-latest.json"
		];

window.onload = function() {
	
	google.charts.load('current', {
	    'packages':['geochart', 'corechart'],
	    // Note: you will need to get a mapsApiKey for your project.
	    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
	    'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
  	});
  	google.charts.setOnLoadCallback(drawRegionsMap);
	google.charts.setOnLoadCallback(drawPieChart);
	google.charts.setOnLoadCallback(drawColumnChart);
}

function openPage(pageName,elmnt,color) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }
  document.getElementById(pageName).style.display = "block";
  //elmnt.style.backgroundColor = color;
  elmnt.style.backgroundColor = "#c9cdf0";
}

function setPage(i) {
	var tabcontent = document.getElementsByClassName("tabcontent");
	var tablinks = document.getElementsByClassName("tablink");
	
	if(i == 0) {
		tabcontent[i].style.display = "block";
		tablinks[i].style.backgroundColor = "#c9cdf0";		
	}
	else if(i == 1 || i == 2) {
		tabcontent[i].style.display = "none";
		tablinks[i].style.backgroundColor = "";
	}
}

function requestData(index) {
	return $.get(url[index]);
}

function drawRegionsMap() {
	requestData(0).done(function(result) {
		var vaccineData = JSON.parse(result);
		var data = google.visualization.arrayToDataTable([
			['Provinces', 'Vaccini somministrati', 'Vaccini consegnati'],
			['Sardegna', vaccineData.data[15].dosi_somministrate, vaccineData.data[15].dosi_consegnate],
			['Sicilia', vaccineData.data[16].dosi_somministrate, vaccineData.data[16].dosi_consegnate], 
			['Friuli-Venezia Giulia', vaccineData.data[5].dosi_somministrate, vaccineData.data[5].dosi_consegnate], 
			['Trentino-Alto Adige', vaccineData.data[12].dosi_somministrate, vaccineData.data[12].dosi_consegnate], 
			['Valle d\'Aosta', vaccineData.data[19].dosi_somministrate, vaccineData.data[19].dosi_consegnate], 
			['Calabria', vaccineData.data[2].dosi_somministrate, vaccineData.data[2].dosi_consegnate],
			['Basilicata', vaccineData.data[1].dosi_somministrate, vaccineData.data[1].dosi_consegnate], 
			['Puglia', vaccineData.data[14].dosi_somministrate, vaccineData.data[14].dosi_consegnate], 
			['Campania', vaccineData.data[3].dosi_somministrate, vaccineData.data[3].dosi_consegnate], 
			['Molise', vaccineData.data[10].dosi_somministrate, vaccineData.data[10].dosi_consegnate], 
			['Abruzzo', vaccineData.data[0].dosi_somministrate, vaccineData.data[0].dosi_consegnate], 
			['Lazio', vaccineData.data[6].dosi_somministrate, vaccineData.data[6].dosi_consegnate],
			['Marche', vaccineData.data[9].dosi_somministrate, vaccineData.data[9].dosi_consegnate], 
			['Umbria', vaccineData.data[18].dosi_somministrate, vaccineData.data[18].dosi_consegnate], 
			['Toscana', vaccineData.data[17].dosi_somministrate, vaccineData.data[17].dosi_consegnate], 
			['Emilia-Romagna', vaccineData.data[4].dosi_somministrate, vaccineData.data[4].dosi_consegnate], 
			['Liguria', vaccineData.data[7].dosi_somministrate, vaccineData.data[7].dosi_consegnate], 
			['Veneto', vaccineData.data[20].dosi_somministrate, vaccineData.data[20].dosi_consegnate],
			['Lombardia', vaccineData.data[8].dosi_somministrate, vaccineData.data[8].dosi_consegnate], 
			['Piemonte', vaccineData.data[13].dosi_somministrate, vaccineData.data[13].dosi_consegnate],
		]);
		//Manca bolzano
	
		var options = {
			region: 'IT', // Southern Europe
			resolution:'provinces',
			colorAxis: {colors: ['#e6f2ff', '#004280']},
			backgroundColor: '#81d4fa',
			datalessRegionColor: '#f8bbd0',
			defaultColor: '#f5f5f5'
		};
	
	    var chart = new google.visualization.GeoChart(document.getElementById('geochart-colors'));
		google.visualization.events.addListener(chart, 'ready', function() {
			setPage(0);
		});				
	    chart.draw(data, options);		
	})
	.fail(function (jqXHR, status, errorThrown) {
	    console.log(errorThrown);
	});
}

function sumCategoryData(json, category) {
	var sum = 0;
	for(var i=0; i<json.data.length; i++) {
		sum += json.data[i][category];
	}
	return sum;
}

function drawPieChart() {
	requestData(1).done(function(result) {
		var categoryData = JSON.parse(result);
		var data = google.visualization.arrayToDataTable([
			['Categoria', 'Dosi'],
			['Fascia 12-19', categoryData.data[0].totale],
			['Fascia 20-29', categoryData.data[1].totale],
			['Fascia 30-39', categoryData.data[2].totale],
			['Fascia 40-49', categoryData.data[3].totale],
			['Fascia 50-59', categoryData.data[4].totale],
			['Fascia 60 - 69', categoryData.data[5].totale],
			['Fascia 70 - 79', categoryData.data[6].totale],
			['Fascia 80+', (categoryData.data[7].totale + categoryData.data[8].totale)]
		]);

		var options = {
			//title: 'My Daily Activities',
			is3D: true,
			width: 900,
			height: 500,
			//chartArea: {width: 900, height: 500}
			backgroundColor: { fill:'transparent' }
		};
	
		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		google.visualization.events.addListener(chart, 'ready', function() {
			setPage(1);
		});
	    chart.draw(data, options);	
	})
	.fail(function (jqXHR, status, errorThrown) {
		console.log(errorThrown);
	});
}

function drawColumnChart() {
	requestData(2).done(function(result) {
		var covidData = JSON.parse(result);
		var data = google.visualization.arrayToDataTable([
			['Regione', 'Nuovi positivi', 'Decessi totali da inizio pandemia', 'Attualmente ospedalizzati (ordinari e TI)'],
			['Sardegna', covidData[15].nuovi_positivi, covidData[15].deceduti, covidData[15].totale_ospedalizzati],
			['Sicilia', covidData[16].nuovi_positivi, covidData[16].deceduti, covidData[16].totale_ospedalizzati],
			['Friuli-Venezia Giulia', covidData[5].nuovi_positivi, covidData[5].deceduti, covidData[5].totale_ospedalizzati],
			['Trentino-Alto Adige', covidData[12].nuovi_positivi, covidData[12].deceduti, covidData[12].totale_ospedalizzati],
			['Valle d\'Aosta', covidData[19].nuovi_positivi, covidData[19].deceduti, covidData[19].totale_ospedalizzati],
			['Calabria', covidData[2].nuovi_positivi, covidData[2].deceduti, covidData[2].totale_ospedalizzati],
			['Basilicata', covidData[1].nuovi_positivi, covidData[1].deceduti, covidData[1].totale_ospedalizzati],
			['Puglia', covidData[14].nuovi_positivi, covidData[14].deceduti, covidData[14].totale_ospedalizzati],
			['Campania', covidData[3].nuovi_positivi, covidData[3].deceduti, covidData[3].totale_ospedalizzati],
			['Molise', covidData[10].nuovi_positivi, covidData[10].deceduti, covidData[10].totale_ospedalizzati],
			['Abruzzo', covidData[0].nuovi_positivi, covidData[0].deceduti, covidData[0].totale_ospedalizzati],
			['Lazio', covidData[6].nuovi_positivi, covidData[6].deceduti, covidData[6].totale_ospedalizzati],
			['Marche', covidData[9].nuovi_positivi, covidData[9].deceduti, covidData[9].totale_ospedalizzati],
			['Umbria', covidData[18].nuovi_positivi, covidData[18].deceduti, covidData[18].totale_ospedalizzati],
			['Toscana', covidData[17].nuovi_positivi, covidData[17].deceduti, covidData[17].totale_ospedalizzati],
			['Emilia-Romagna', covidData[4].nuovi_positivi, covidData[4].deceduti, covidData[4].totale_ospedalizzati],
			['Liguria', covidData[7].nuovi_positivi, covidData[7].deceduti, covidData[7].totale_ospedalizzati],
			['Veneto', covidData[20].nuovi_positivi, covidData[20].deceduti, covidData[20].totale_ospedalizzati],
			['Lombardia', covidData[8].nuovi_positivi, covidData[8].deceduti, covidData[8].totale_ospedalizzati],
			['Piemonte', covidData[13].nuovi_positivi, covidData[13].deceduti, covidData[13].totale_ospedalizzati]
		]);
		//Manca Bolzano
		
		var options = {
			width: 1100,
			height: 600,
			hAxis: {
				logscale:true,
				scaleType:"mirrorLog"
			},
			
			bar: {groupWidth: "90%"}
			
			//chartArea: {  width: "50%", height: "70%" }
		};
	
		var chart = new google.visualization.BarChart(document.getElementById('columnchart_material'));
		google.visualization.events.addListener(chart, 'ready', function() {
			setPage(2);
		});
		chart.draw(data, options);
	})
	.fail(function (jqXHR, status, errorThrown) {
		console.log(errorThrown);
	});
}