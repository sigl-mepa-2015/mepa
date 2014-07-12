$(function() {
	initPies();
});

function initPies(){
	
	$('#pieContainer canvas').each(function(){
		new Chart(($(this).get(0).getContext("2d"))).Doughnut($.parseJSON($(this).attr('data-json')), []);
	});
}