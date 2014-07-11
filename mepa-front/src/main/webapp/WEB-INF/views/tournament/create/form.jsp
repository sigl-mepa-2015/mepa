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
    <form:form role="form" action="${addTournamentFormActionUrl}" cssClass="row" cssStyle="margin:auto;" modelAttribute="tournament" method="post">
        <div class="form-group">
            <div class="input-group col-md-4">
                <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
                <label for="name"><spring:message code="tournament.form.nameLabel" /></label>
                <spring:message code='tournament.form.namePlaceholder' var="namePlaceholder"/>
                <form:input id="name" path="name" class="form-control" type="text" placeholder="${namePlaceholder}" required="required"/>
            </div>
            <div class="input-group col-md-4">
                <form:errors path="maxTeamNumber" cssStyle="color: #FF0000;" htmlEscape="false" />
                <label for="maxTeamNumber"><spring:message code="tournament.form.maxTeamNumberLabel" /></label>
                <spring:message code='tournament.form.maxTeamNumberPlaceholder' var="maxTeamNumberPlaceholder"/>
                <form:input id="maxTeamNumber" path="maxTeamNumber" class="form-control" min="0" required="required" type="number" placeholder="${maxTeamNumberPlaceholder}"/>
            </div>
            <div class="input-group col-md-4">
                <form:errors path="type" cssStyle="color: #FF0000;" htmlEscape="false"/>
                <label for="type"><spring:message code="tournament.form.typeLabel" /></label>
                <spring:message code='tournament.form.typePlaceholder' var="typePlaceholder"/>
                <form:input id="type" path="type" class="form-control" type="text" placeholder="${typePlaceholder}"/>
            </div>
            <div class="input-group col-md-4">
                <br>
                <button type="submit" class="btn btn-success pull-right"><spring:message code="add" /></button>
            </div>
        </div>
    </form:form>
</div>

