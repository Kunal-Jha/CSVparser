$(document).ready(function() {
	$("#classesSelction").hide();
	$("#resultsRanking").hide();
	$("#datasetList").submit(function(e) {
		e.preventDefault();
	});
	$("#classList").submit(function(e) {
		e.preventDefault();
	});
});

function getClassList() {
	// get the form data using another method
	var e = document.getElementById("selectedDataset");
	datasetName = e.options[e.selectedIndex].value;
	$.ajax({
		url : "/getClass",// servlet URL that gets
		type : "POST",// request type, can be GET
		data : {
			datasetName : datasetName,// data to be sent
		},
		dataType : "json",// type of data returned
		success : function(data) {
			printClasses(data);
		}
	})
};

function printClasses(data) {
	$("#dataset").hide();
	$("#classesSelction").show();
	$("#resultsRanking").hide();
	classes = data;
	var content = "<label> <input type=\"radio\" class=\"option-input radio\" "
			+ "name=\"class\" /  value= \"All\"  > All " + "</label> </br>";
	$
			.each(
					classes,
					function(i, v) {
						content += "<label> <input type=\"radio\" class=\"option-input radio\" "
								+ "name=\"class\" /  value=\""
								+ v.uri
								+ "\">" + v.uri + "</label> </br>"
					});

	content += "<div class=\"form-group row\">"
			+ "<div class=\"offset-sm-2 col-sm-10\">"
			+ "<button type=\"submit\"class=\"btn btn-outlinesubmit btn-xl js-scroll-trigger\" onclick=\"getRanks()\">Submit</button> </div>"
			+ "</div>"

	$("#classList").append($(content));
}

function getRanks() {
	// get the form data using another method
	var className = document.querySelector('input[name = "class"]:checked').value;
	$.ajax({
		url : "/rankResults",// servlet URL that gets
		type : "POST",// request type, can be GET
		data : {
			className : className,// data to be sent
		},
		dataType : "json",// type of data returned
		success : function(data) {
			printRanks(data);
		}
	})
};

function printRanks(data) {
	$("#dataset").hide();
	$("#classesSelction").hide();
	$("#resultsRanking").show();
	ranks = data;
	var content = "<h2 class=\"section-heading\">Entities and Triples With Rankings</h2>"
			+ "<table class=\"table\">"
			+ "<thead class=\"thead-inverse\">"
			+ "<tr>"
			+ "<th>#</th>"
			+ "<th>Entities</th>"
			+ "<th>Rank</th>"
			+ "</tr>" + "</thead> <tbody>";
	var counter = 1;
	$.each(ranks, function(i, v) {
		content += "<tr> <th scope=\"row\">" + counter + "</th> " + "<td>"
				+ v.uri + "</td><td>" + v.rank + "</td></tr>"

		counter += 1;
	});

	content += "</tbody></table>"

	$("#rankTable").append($(content));
}
