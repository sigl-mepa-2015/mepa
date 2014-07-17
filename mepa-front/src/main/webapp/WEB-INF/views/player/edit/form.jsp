<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
    <div class="page-header">
        <h1><spring:message code="player.editTitle" /> : ${player.name} ${player.firstname}</h1>
    </div>
    <form:form role="form" action="${pageContext.request.contextPath}/player/edit?teamID=${player.team.id}" modelAttribute="PlayerFormBean" method="post">
        <div class="form-group col-md-3">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <label for="name"><spring:message code="player.form.nameLabel" /></label>
            <br/>
            <spring:message code='player.form.namePlaceholder' var="namePlaceholder"/>
            <form:input id="name" path="name" class="form-control" type="text" maxlength="20" placeholder="${namePlaceholder}"/>
            <spring:message code='player.form.firstnamePlaceholder' var="firstnamePlaceholder"/>
            <form:input id="firstname" path="firstname" class="form-control" type="text" maxlength="20" placeholder="${firstnamePlaceholder}"/>
            <form:input id="id" path="id" class="form-control" maxlength="20" value="${player.id}" type="hidden" placeholder="${namePlaceholder}"/>
            <br/>
            <button type="submit" class="btn btn-primary">
                <spring:message code="update" /></button>
        </div>
    </form:form>
</div>