<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/team/namespace.jsp"%>
<div class="container">
    <div class="alert alert-success">
        <spring:message code="team.create.resultMessage" arguments="${team.name}" />
    </div>
    <p>
        <a href="${teamUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> <spring:message code="team.backToList"/>
        </a>
    </p>
</div>
