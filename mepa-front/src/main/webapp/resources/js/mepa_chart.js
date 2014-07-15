$(function() {
	initPies();
	initPiesGame();
	
	var languageFR = {
		    "sProcessing":     "Traitement en cours...",
		    "sSearch":         "Rechercher&nbsp;:",
		    "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
		    "sInfo":           "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
		    "sInfoEmpty":      "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
		    "sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
		    "sInfoPostFix":    "",
		    "sLoadingRecords": "Chargement en cours...",
		    "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
		    "sEmptyTable":     "Aucune donnée disponible dans le tableau",
		    "oPaginate": {
		        "sFirst":      "Premier",
		        "sPrevious":   "Pr&eacute;c&eacute;dent",
		        "sNext":       "Suivant",
		        "sLast":       "Dernier"
		    },
		    "oAria": {
		        "sSortAscending":  ": activer pour trier la colonne par ordre croissant",
		        "sSortDescending": ": activer pour trier la colonne par ordre décroissant"
		    }
	};
	
	$("#rangeTable").dataTable({
		"oLanguage": languageFR,
		"bSortable": true,
		"sPaginationType": "full_numbers",
		"aaSorting": [],
		"aoColumns": [{ "bSortable": false }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }]
	});
	
	$("#playerRange").dataTable({
		"oLanguage": languageFR,
		"bSortable": true,
		"sPaginationType": "full_numbers",
		"aaSorting": [],
		"aoColumns": [{ "bSortable": false }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }]
	});
	
	$("#playersTable").dataTable({
		"oLanguage": languageFR,
		"bSortable": true,
		"sPaginationType": "full_numbers",
		"aaSorting": []
	});

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

function initPiesGame(){
	
	var data = [
	            {
	                value: 300,
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
	        ]
	
	$('#pieGame,#pieGame2').each(function(){
		new Chart(($(this).get(0).getContext("2d"))).Doughnut(data, []);
	});
}