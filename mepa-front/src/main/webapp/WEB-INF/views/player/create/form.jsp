<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/team/namespace.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Appartient à ${team.name}</h1>
        <h1><spring:message code="player.createTitle" /></h1>
    </div>
    <form:form role="form" action="${pageContext.request.contextPath}/player/create?teamID=${team.id}" modelAttribute="PlayerFormBean" method="post">
        <div class="form-group col-md-3">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <label for="name"><spring:message code="player.form.nameLabel" /></label>
            <br/>
            <spring:message code='player.form.namePlaceholder' var="namePlaceholder"/>
            <form:input id="name" path="name" class="form-control" type="text" maxlength="20" placeholder="${namePlaceholder}"/>
            <spring:message code='player.form.firstnamePlaceholder' var="firstnamePlaceholder"/>
            <form:input id="firstname" path="firstname" class="form-control" type="text" maxlength="20" placeholder="${firstnamePlaceholder}"/>
            <br/>
            <button type="submit" class="btn btn-primary">
                <spring:message code="add" /></button>
        </div>
    </form:form>
</div>
