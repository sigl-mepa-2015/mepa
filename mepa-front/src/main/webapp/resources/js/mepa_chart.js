$(function() {
	initPies();
	
	$("#rangeTable").dataTable({
		"oLanguage": {
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
		},
		"bSortable": true,
		"sPaginationType": "full_numbers",
		"aaSorting": [],
		"aoColumns": [{ "bSortable": false }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }, { "bSortable": true }]
	});
});

function initPies(){
	
	$('#pieContainer canvas').each(function(){
		new Chart(($(this).get(0).getContext("2d"))).Doughnut($.parseJSON($(this).attr('data-json')), []);
	});
}