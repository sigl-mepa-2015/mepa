$(function() {
	initPies();

        colorWinners();
});

function colorWinners() {
    $('.scoreRow').each(function() {
        var score1 = parseInt($(".score1", $(this)).html());
        var score2 = parseInt($(".score2", $(this)).html());
        if (score1 > score2) {
            $("td:first-child", $(this)).addClass('success');
            $('.score1', $(this)).addClass("success");
        }
        else if (score2 > score1) {
            $("td:last-child", $(this)).addClass('success');
            $('.score2', $(this)).addClass("success");
        }
    });
}

function initPies(){
	
	$('#pieContainer canvas').each(function(){
		new Chart(($(this).get(0).getContext("2d"))).Doughnut($.parseJSON($(this).attr('data-json')), []);
	});
}