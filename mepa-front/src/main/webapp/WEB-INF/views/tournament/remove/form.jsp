<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<form:form role="form" action="${removeTournamentActionUrl}" modelAttribute="removeTournamentFormBean"  method="post" cssStyle="display : inline-block">
    <form:input path="id" name="id" type="hidden" value="${t.id}"/>
    <button class="btn btn-danger"><i class="glyphicon glyphicon-trash"></i></button>
</form:form>