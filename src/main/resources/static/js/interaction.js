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

	var content = "<table class=\"table\"> " + "<thead> " + "<tr> "
			+ "<th scope=\"col\">#</th>" + "<th scope=\"col\">Query</th>"
			+ "<th scope=\"col\">File</th>" + "<th scope=\"col\">Result</th>"
			+ "</tr></thead> <tbody>"
	content += "<tr> <th scope=\"row\">" + counter + "</th> " + "<td>"
			+ queryFinal + "</td> " + "<td>" + documentName + "</td> " + "<td>"
			+ JSON.stringify(results) + "</td>" + "</tr>"

	content += "</tbody></table>"
	counter += 1;

	$("#resultTable").append($(content));
}

function buildHtmlTable(data) {
	$("#resultsection").show();
	results = data;
	var col = [];
	for (var i = 0; i < results.length; i++) {
		for ( var key in results[i]) {
			if (col.indexOf(key) === -1) {
				col.push(key);
			}
		}
	}

	var table = document.createElement("table");

	var tr = table.insertRow(-1); // TABLE ROW.

	for (var i = 0; i < col.length; i++) {
		var th = document.createElement("th"); // TABLE HEADER.
		th.innerHTML = col[i];
		tr.appendChild(th);
	}

	// ADD JSON DATA TO THE TABLE AS ROWS.
	for (var i = 0; i < results.length; i++) {

		tr = table.insertRow(-1);

		for (var j = 0; j < col.length; j++) {
			var tabCell = tr.insertCell(-1);
			tabCell.innerHTML = myBooks[i][col[j]];
		}
	}

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById("resultTable");
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
}
