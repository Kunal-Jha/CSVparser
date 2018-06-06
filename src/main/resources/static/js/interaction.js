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
	var content = "";
	content += "<tr> <th scope=\"row\">" + counter + "</th> " + "<td>"
			+ queryFinal + "</td> " + "<td>" + documentName + "</td> " + "<td>"
			+ JSON.stringify(results) + "</td>" + "</tr>"
	counter += 1;

	$("#row").append($(content));

}
