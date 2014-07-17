<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
    <div class="page-header">
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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${team.players}" var="player">
            <tr>
                <td width="70%">${player.name}</td>


                <td width="70%">${player.firstname}</td>


                        <td width="70%"></td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>