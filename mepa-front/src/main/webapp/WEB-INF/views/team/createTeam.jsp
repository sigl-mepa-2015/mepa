<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>

<div class="container">
    <div class="page-header">
        <h1><spring:message code="team.create.form" /></h1>
    </div>
    <form:form role="form" action="/team/create" method="post">
        <div class="form-group">
            <label for="name"><spring:message code="tournament.form.nameLabel" /></label>
            <br/>
        </div>
        <button type="submit" class="btn btn-primary"><spring:message code="add" /></button>
    </form:form>
</div>
