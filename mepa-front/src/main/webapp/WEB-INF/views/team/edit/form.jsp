<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/team/namespace.jsp"%>

<div>
    <div class="page-header">
        <h1><spring:message code="team.editTitle" /> : ${team.name}</h1>
    </div>
    <form:form role="form" action="${editTeamFormActionUrl}" modelAttribute="addTeamFormBean" method="post">
        <div class="form-group col-md-3">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <label for="name"><spring:message code="team.form.nameLabel" /></label>
            <br/>
            <spring:message code='team.form.namePlaceholder' var="namePlaceholder"/>
            <form:input id="name" path="name" class="form-control" type="text" maxlength="20" placeholder="${namePlaceholder}" required="required"/>
            <form:input id="id" path="id" class="form-control" maxlength="20" value="${team.id}" type="hidden" placeholder="${namePlaceholder}"/>
            <br/>
            <button type="submit" class="btn btn-primary"><spring:message code="update" /></button>
        </div>
    </form:form>
</div>
