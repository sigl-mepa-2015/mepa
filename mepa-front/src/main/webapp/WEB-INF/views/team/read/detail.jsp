<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Détail de l'équipe ${team.name}</h1>
    </div>
</div>

<div class="container">
<div class="col-md-9 col-md-offset-1">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom du joueur</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${team.players}" var="player">
            <tr>
                <td width="70%">${player.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>