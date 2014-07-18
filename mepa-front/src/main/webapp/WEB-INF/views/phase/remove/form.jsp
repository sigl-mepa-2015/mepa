<%--
  User: Quentin
  Date: 18/07/2014
  Time: 1:02
--%>
<%@ include file="/WEB-INF/views/phase/namespace.jsp" %>
<form:form id="removePhaseForm-${p.id}" role="form" action="${removePhaseActionUrl}"
           modelAttribute="removePhaseFormBean" method="post" cssStyle="display : inline-block">
    <form:input path="id" name="id" type="hidden" value="${p.id}"/>
    <spring:message code="delete" var="removeLabel"/>
    <span data-toggle="modal" data-target="#removePhaseForm"
          data-href="javascript:$('#removePhaseForm-${p.id}').submit()" class="confirm btn btn-danger"
          title="${removeLabel}"><i class="glyphicon glyphicon-trash"></i></span>
</form:form>