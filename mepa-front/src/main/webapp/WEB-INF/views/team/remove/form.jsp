<%-- 
    Document   : form
    Created on : 17 juil. 2014, 23:16:04
    Author     : david
--%>

<%@ include file="/WEB-INF/views/team/namespace.jsp"%>
<form:form id="delete-${t.id}" role="form" action="${removeTeamActionUrl}" modelAttribute="removeTeamFormBean"  method="post" cssStyle="display : inline-block">
    <form:input path="id" name="id" type="hidden" value="${t.id}"/>
    <spring:message code="delete" var="removeLabel" />
    <span data-toggle="modal" data-target="#confirm-delete" data-href="javascript:$('#delete-${t.id}').submit()" class="confirm btn btn-danger" title="${removeLabel}"><i class="glyphicon glyphicon-trash"></i></span>
</form:form>
