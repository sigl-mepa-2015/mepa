<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<form:form id="delete-${t.id}" role="form" action="${removeTournamentActionUrl}" modelAttribute="removeTournamentFormBean"  method="post" cssStyle="display : inline-block">
    <form:input path="id" name="id" type="hidden" value="${t.id}"/>
    <spring:message code="delete" var="removeLabel" />
    <span data-toggle="modal" data-target="#confirm-delete" data-href="javascript:$('#delete-${t.id}').submit()" class="confirm btn btn-danger" title="${removeLabel}"><i class="glyphicon glyphicon-trash"></i></span>
</form:form>