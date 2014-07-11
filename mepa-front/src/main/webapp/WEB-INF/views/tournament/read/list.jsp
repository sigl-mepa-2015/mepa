<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>

<div class="container">
	<div class="page-header">
        <spring:message code="add" var="addLabel" />
        <a class="pull-right btn btn-success" href="${addTournamentFormUrl}" title="${addLabel}"><i class="glyphicon glyphicon-plus"></i></a>
		<h1>Tous les tournois</h1>
	</div>
</div>

<div class="container">

        <c:if test="${not empty removed}">
            <div id="message_box" class="alert alert-success">
                <spring:message code="tournament.remove.resultMessage" arguments="${removed}" />
            </div>
        </c:if>
        <c:if test="${not empty updated}">
            <div id="message_box" class="alert alert-success">
                <spring:message code="tournament.update.resultMessage" arguments="${updated}" />
            </div>
        </c:if>
        <c:if test="${not empty created}">
            <div id="message_box" class="alert alert-success">
                <spring:message code="tournament.create.resultMessage" arguments="${created}" />
            </div>
        </c:if>

<div class="col-md-9 col-md-offset-1">
    <c:choose>
        <c:when test="${not empty tournaments}">
            <table class="table table-striped" style="text-align: center">
                <thead>
                    <tr>
                        <th>Nom du tournoi</th>
                        <th>Type de tournoi</th>
                        <th>Nombre maximum d'équipes</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tournaments}" var="t">
                        <tr>
                            <td >${t.name}</td>
                            <td >${t.type}</td>
                            <td >${t.maxTeamNumber}</td>
                            <td class="col-md-4">
                                <button type="button" class="btn btn-primary" >
                                    <span class="glyphicon glyphicon-arrow-right"></span>
                                </button>
                                <button type="button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath}/reporting/tournament?tournamentID=${t.id}'">
                                    <span class="glyphicon glyphicon-stats"></span>
                                </button>
                                <spring:message code="update" var="updateLabel" />
                                <a href="${pageContext.request.contextPath}/tournament/form/${t.id}" title="${updateLabel}" class="btn btn-default" >
                                    <span class="glyphicon glyphicon-cog"></span>
                                </a>
                                <%@ include file="/WEB-INF/views/tournament/remove/form.jsp"%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="jumbotron">
                <div class="container">
                    <h2><spring:message code="tournament.emptyTitle" /></h2>
                    <a class="btn-lg btn btn-primary" href="${addTournamentFormUrl}" title="${addLabel}"><i class="glyphicon glyphicon-plus"></i> <spring:message code="add" /></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</div>

<script>
	$("#message_box").delay(1500).slideUp();
</script>
