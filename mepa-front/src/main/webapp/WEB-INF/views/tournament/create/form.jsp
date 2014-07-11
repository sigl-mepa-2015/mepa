<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<div class="container">
    <div class="page-header">
        <h1><spring:message code="tournament.createTitle" /></h1>
    </div>
    <form:form role="form" action="${addTournamentFormActionUrl}" modelAttribute="addTournamentFormBean" method="post">
        <div class="form-group col-md-3">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <label for="name"><spring:message code="tournament.form.nameLabel" /></label>
            <br/>
            <spring:message code='tournament.form.namePlaceholder' var="namePlaceholder"/>
            <form:input id="name" path="name" class="form-control" type="text" maxlength="20" placeholder="${namePlaceholder}"/>
            <br/>
            <button type="submit" class="btn btn-primary"><spring:message code="add" /></button>
        </div>
    </form:form>
</div>

