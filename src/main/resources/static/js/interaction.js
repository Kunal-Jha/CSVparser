$(document).ready(function() {
	$("#resultsection").hide();
	$("#datasetList").submit(function(e) {
		e.preventDefault();
	});

});

var documentName;
var queryFinal;
var counter = 1;

function getResult() {
	var e = document.getElementById("selectedFile")
	fileName = e.options[e.selectedIndex].value;
	query = document.getElementById("query").value;
	queryFinal = query;

	documentName = fileName;
	$.ajax({
		url : "/parser",// servlet URL
		type : "GET",
		data : {
			fileName : fileName,
			query : query
		// data to be sent
		},
		dataType : "json",// type of data returned
		success : function(data) {
			printResults(data);
		}
	})
};

function printResults(data) {
	$("#resultsection").show();
	results = data;
	var content = +"<table class=\"table\">  <thead> <tr> <th scope=\"col\">#</th><th scope=\"col\">Query</th><th scope=\"col\">File</th><th scope=\"col\">Result</th> <th scope=\"col\">TimeStamp</th></tr></thead>"

	content += "<tr> <th scope=\"row\">" + counter + "</th> " + "<td>"
			+ queryFinal + "</td><td>" + documentName + "</td> <td>"
			+ JSON.stringify(results) + "</td> <td>"
			+ JSON.stringify(results.timestamp) + "</td></tr>"
	counter += 1;

	$("#resultTable").append($(content));
}
