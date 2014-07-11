<%--
  Created by IntelliJ IDEA.
  User: Nyfanilo
  Date: 11/07/2014
  Time: 18:52
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<div class="container">
    <div class="alert alert-success">
        <spring:message code="tournament.update.resultMessage" arguments="${tournament.name}" />
    </div>
    <p>
        <a href="${tournamentUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> <spring:message code="tournament.backToList"/>
        </a>
    </p>
</div>
