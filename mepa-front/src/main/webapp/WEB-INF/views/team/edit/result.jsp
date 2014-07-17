<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/team/namespace.jsp"%>
<div>
    <div class="alert alert-success">
        <spring:message code="team.edit.resultMessage" arguments="${team.name}" />
    </div>
    <p>
        <a href="${tournamentUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> <spring:message code="team.backToList"/>
        </a>
    </p>
</div>
