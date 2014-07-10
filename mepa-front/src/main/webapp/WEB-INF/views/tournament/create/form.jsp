<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<div class="container">
    <div class="page-header">
        <h1><spring:message code="tournament.createTitle" /></h1>
    </div>
    <form:form role="form" action="${addTournamentFormActionUrl}" modelAttribute="addTournamentFormBean" method="post">
        <div class="form-group">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <p class="help-block"><spring:message code="tournament.form.nameHelp" /></p>
            <label for="name"><spring:message code="tournament.form.nameLabel" /></label>
            <br/>
            <spring:message code='tournament.form.namePlaceholder' var="namePlaceholder"/>
            <form:input id="name" path="name" class="form-control" type="text" maxlength="20" placeholder="${namePlaceholder}"/>
        </div>
        <button type="submit" class="btn btn-primary"><spring:message code="add" /></button>
    </form:form>
</div>

