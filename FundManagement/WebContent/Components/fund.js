$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validatefundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	//If valid------------------------
	var type = ($("#hidfundidSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "fundAPI",
		type : type,
		data : $("#formfund").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onfundSaveComplete(response.responseText, status);
		}
	});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidfundidSave").val($(this).closest("tr").find('#hidfundidUpdate').val());
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pnumber").val($(this).closest("tr").find('td:eq(2)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(3)').text());
	$("#city").val($(this).closest("tr").find('td:eq(4)').text());
	$("#desc").val($(this).closest("tr").find('td:eq(5)').text());
});

//Delete

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "fundAPI",
		type : "DELETE",
		data : "fundid=" + $(this).data("fundId"),
		dataType : "text",
		complete : function(response, status) {
			onfundDeleteComplete(response.responseText, status);
		}
	});
});
// CLIENT-MODEL================================================================
function validatefundForm() {
	// name
	if ($("#name").val().trim() == "") {
		return "Insert funder name.";
	}
	// amount
	if ($("#amount").val().trim() == "") {
		return "Insert Fund Amount.";
	}

	//pnumber-------------------------------
	if ($("#pnumber").val().trim() == "") {
		return "Insert Mobile Number.";
	}
	//nic
	if ($("#nic").val().trim() == "") {
		return "Insert nic.";
	}
	// city
	if ($("#city").val().trim() == "") {
		return "Insert city.";
	}

	// is numerical value
	var tmpamount = $("#amount").val().trim();
	if (!$.isNumeric(tmpamount)) {
		return "Insert a numerical value for amount.";
	}
	// convert to decimal price
	$("#amount").val(parseDouble(tmpamount).toFixed(2));
	// DESCRIPTION------------------------
	if ($("#desc").val().trim() == "") {
		return "Insert Fund Description.";
	}
	
	return true;
}

function onfundSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divfundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidfundidSave").val("");
	$("#formfund")[0].reset();
}

function onfundDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divfundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}