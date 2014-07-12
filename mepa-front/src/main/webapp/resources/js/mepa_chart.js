$(document).ready(function() {
	
	var x = 50;
	var data = [
	            {
	                value: x,
	                color:"#F7464A",
	                highlight: "#FF5A5E",
	                label: "Red"
	            },
	            {
	                value: 50,
	                color: "#46BFBD",
	                highlight: "#5AD3D1",
	                label: "Green"
	            },
	            {
	                value: 100,
	                color: "#FDB45C",
	                highlight: "#FFC870",
	                label: "Yellow"
	            }
	        ];
	var ctx = document.getElementById("myChart").getContext("2d");
	var ctx2 = document.getElementById("myChart2").getContext("2d");
	var myDoughnutChart = new Chart(ctx).Doughnut(data,[]);
	var myDoughnutChart2 = new Chart(ctx2).Doughnut(data,[]);
	
})

