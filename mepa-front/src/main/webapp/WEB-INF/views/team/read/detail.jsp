<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
    <div class="page-header">
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/result/teamScore?teamID=${team.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="result.bar.title.nav2"/>
        </button>
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/player/form?teamID=${team.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title2.nav3"/>
        </button>
        <h1>Détail de l'équipe ${team.name}</h1>
    </div>
</div>

<div>
<div class="col-md-9 col-md-offset-1">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom du joueur</th>
            <th>Prénom du joueur</th>
            <th>Editer</th>
            <th>Supprimer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${team.players}" var="player">
            <tr>
                <td width="70%">${player.name}</td>


                <td width="70%">${player.firstname}</td>

<td>
                <button type="button" class="btn btn-info" onClick="location.href = '${pageContext.request.contextPath}/player/edit?playerID=${player.id}'">
                    <span class="glyphicon glyphicon-cog"></span>
                </button>
</td>
                <td>
                    <button type="button" class="btn btn-danger" onClick="location.href = '${pageContext.request.contextPath}/player/delete?playerID=${player.id}&teamID=${team.id}'">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>