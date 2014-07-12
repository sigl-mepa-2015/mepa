<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
    <div class="page-header">
    <a class="pull-right btn btn-success" title="Créer une poule" href="${pageContext.request.contextPath}/creerPoule"><i class="glyphicon glyphicon-plus"></i> Créer une poule</a>
        <h1>Tournoi ${tournamentView.name} </h1>
    </div>
</div>
<div class="container">
    Maximum d'équipes allouables : ${tournamentView.maxTeamNumber}<br/>
    Liste des équipes : ...<br/>
    Liste des pules : ...<br/>
</div>
