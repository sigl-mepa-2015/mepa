<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
    <div class="page-header">
        <a class="pull-right btn btn-success" title="Créer une poule" href="${pageContext.request.contextPath}/creerPoule?tournamentID=${tournamentView.id}">
            <i class="glyphicon glyphicon-plus"></i> Créer une poule
        </a>
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/team/form?tournamentID=${tournamentView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title2.nav1"/>
        </button>
        <h1>Tournoi ${tournamentView.name} </h1>
    </div>
</div>
<div class="container">
    <h2>Maximum d'équipes allouables : <c:if test="${tournamentView.maxTeamNumber <0}">-</c:if>${tournamentView.maxTeamNumber}</h2><br/>
    <h2>Liste des équipes :</h2> <br/>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nom de l'équipe</th>
                <th>Tournoi</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tournamentView.teams}" var="t">
                <tr>
                    <td width="70%">${t.name}</td>
                    <td width="70%">${t.tournament.name}</td>
                    <td width="30%">
                        <button type="button" class="btn btn-info" onClick="location.href = '${pageContext.request.contextPath}/team/edit?teamID=${t.id}'">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table><br/>
    <h2>Liste des poules : </h2><br/>
    <table class="table table-striped table-bordered" id="rangeTable">
        <thead>
        <tr>
            <th>Nom de la Poule</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tournamentView.pools}" var="p">
            <tr>
                <td>${p.name} </td>
                <td><button type="button" class="btn btn-primary" onClick="location.href='${pageContext.request.contextPath}/afficherGame?poolID=${p.id}'">
                    <span class="glyphicon glyphicon-arrow-right"></span>
                </button></td>
            </tr>

        </c:forEach>
        </tbody>
        </table>
</div>
