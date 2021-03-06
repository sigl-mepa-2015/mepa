<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Equipe : ${team.name}</h1>
    </div>
</div>
    <c:if test="${valid == 1}" >
        <div id="message_box" class="alert alert-success">
            <spring:message code="phase.teamScore.updateScoreSuccess" />
        </div>
    </c:if>
    <c:if test="${valid == 0}" >
        <div id="message_box" class="alert alert-warning">
            <spring:message code="phase.teamScore.updateScoreError" />
        </div>
    </c:if>
    <script>
        $("#message_box").delay(3000).slideUp();
    </script>
<div>

   </br>
    <table class="table table-striped table-bordered" id="rangeTable">
        <thead>
            <tr>
                <th>Joueur</th>
                <th>Nb points marqués</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${playerList}" var="p" varStatus="loop">
                <form:form id="myform" role="form" action="${pageContext.request.contextPath}/result/modifyPlayerScore" method="post">
                    <tr>
                        <input type="HIDDEN" name="playerID" value="${p.id}">
                        <td>${p.name}</td>
                        <td><input type="text" name="playerScore" size="2" value="${p.nbPoint}"/></td>
                        <td><input type="submit" value="Valider"/></td>
                    </tr>
                </form:form>
            </c:forEach>
        </tbody>
    </table>
    <a class="pull-left btn btn-default" href="/mepa-front/tournament/" title="Annuler">
        <i class="glyphicon glyphicon-arrow-left"></i>
        Annuler
    </a>
</div>