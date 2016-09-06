/**
 *Sample java script controller for  BI Webtools
 *
 */
/*** Global Parameters***/
controller= "sample"; //"controller" will be defined in the projects web.xml file. Will most likely be the Java Servlet Controller object.
/***End of Global Parameters***/
$(document).ready(function(){ 
	// do stuff
	console.log("document Ready");
	$("#processingWrapper").css("display","none");
	
	// always use .off().on() for event based logic
	$("#postButton").off().on("click", function(){
		aDataPrepFunction();
	});
	
	$("#getButton").off().on("click", function(){
		console.log("event fired");
		aDataPrepFunction2();
	});
});

/*Additional logic functions not handled in $(document).ready()*/
function aDataPrepFunction() {
	//this is a data preparation function to format the any data into so that it can be sent over to an Ajax Controller
	// all formatted data must contain an action parameter specifying what this function/method/logic this data is associated with
	var param1 = "value1";
	var data = {"data1": param1, "data2": 3, "data3": true};
	
	sampleController("POST", data, "/sampleaction1" );
	
}

function aDataPrepFunction2() {
	//this is a data preparation function to format the any data into so that it can be sent over to an Ajax Controller
	// all formatted data must contain an action parameter specifying what this function/method/logic this data is associated with
	console.log("data preped");
	var param1 = "value1";
	var data = {"data1": param1, "data2": 3, "data3": true};
	
	sampleController("GET", data,"/sampleaction2");
	
}

/*End of Data Prep Functions*/
/*Controller Functions*/
function sampleController(requestType, data, action) {
	console.log("in controller");
	$.ajax({
		type: requestType,
		data: data,
		url:controller+action,
		dataType: "json",
		beforeSend: function() {
			//do Stuff if needed	
			$("#processingWrapper").css("display","block");
		},
		complete: function() {
			$("#processingWrapper").css("display","none");
		},
		success: function(response) {
			responseHandler(action,response);
		},
		error: function(xhr, status, error) {
			// display error message in notification bar.
			 $("#processingWrapper").hide();
			 $("#wrapper").css("display", "inline-block");
			 $("#notification").css("display", "inline-block");
			 $("#notification").text("sampleController() failed! " + "Error: " + status + ". HTTP status: " + error + ".");
			 $("#notification").removeClass("greenback");
			 $("#notification").addClass("redback");
		}
	});
}
/* End of Controller Functions*/

/*Response Handlers*/
function responseHandler(action, response) {
	//based on the action parameter run the appropriate response function
	console.log("in Handler")
	if (action == "/sampleaction1") {
		sampleAction1Response(response);
	}
	else if(action == "/sampleaction2") {
		sampleAction2Response(response);
	}	
	else {
		$("#notification").css("display", "inline-block");	
		$("#notification").text("Something went wrong...");
		$("#notification").removeClass("greenback");
		$("#notification").addClass("redback");
			
		}	
	}

function sampleAction1Response(response) { 
	console.log("in sampleAction1Response")
	alert(response)
}
function sampleAction2Response(response) {
	console.log("in sampleAction2Response")
	alert(response)
}
/*End of Response Handlers*/
/*END OF File*/