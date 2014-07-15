<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
    <div class="page-header">
        <a class="pull-right btn btn-success" title="Créer une poule" href="${pageContext.request.contextPath}/creerPoule?tournamentID=${tournamentView.id}">
            <i class="glyphicon glyphicon-plus"></i> Créer une poule
        </a>
        <button type="button" class="pull-right btn btn-success" onClick="location.href='${pageContext.request.contextPath}/team/form?tournamentID=${tournamentView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title2.nav1"/>
        </button>
        <h1>Tournoi ${tournamentView.name} </h1>
    </div>
</div>
<div class="container">
    <h2>Maximum d'équipes allouables : <c:if test="${tournamentView.maxTeamNumber <0}">-</c:if>${tournamentView.maxTeamNumber}</h2><br/>
    Liste des équipes : ...<br/>
    <h2>Liste des poules : </h2><br/>
    <table class="table table-striped table-bordered" id="rangeTable">
        <thead>
        <tr>
            <th>Nom de la Poule</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tournamentView.pools}" var="p">
            <tr>
                <td>${p.name}</td>
                </tr>

        </c:forEach>
        </tbody>
        </table>
</div>
