<%--
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp" %>
<form:form id="removeTournamentForm-${t.id}" role="form" action="${removeTournamentActionUrl}"
           modelAttribute="removeTournamentFormBean" method="post" cssStyle="display : inline-block">
    <form:input path="id" name="id" type="hidden" value="${t.id}"/>
    <spring:message code="delete" var="removeLabel"/>
    <span data-toggle="modal" data-target="#removeTournamentForm"
          data-href="javascript:$('#removeTournamentForm-${t.id}').submit()" class="confirm btn-sm btn btn-danger"
          title="${removeLabel}"><i class="glyphicon glyphicon-trash"></i></span>
</form:form>