<%--
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ include file="/WEB-INF/views/phase/namespace.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
            class="sr-only"><spring:message code="close"/></span></button>
    <h1><c:choose>
        <c:when test="${not empty phase.id}">
            <spring:message code="phase.updateTitle"/>
        </c:when>
        <c:otherwise>
            <spring:message code="phase.createTitle"/>
        </c:otherwise>
    </c:choose></h1>
</div>
<form:form role="form" action="${addPhaseFormActionUrl}" cssClass="row" cssStyle="margin:auto;"
           modelAttribute="phase" method="post">
    <div class="modal-body">
        <div class="form-group">
            <form:errors path="id" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <form:input id="id" path="id" type="hidden"/>
            <form:errors path="tournament.id" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <form:input id="tournament.id" path="tournament.id" type="hidden"/>
            <div class="input-group col-md-4">
                <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
                <label for="name"><spring:message code="phase.form.nameLabel"/></label>
                <spring:message code='phase.form.namePlaceholder' var="namePlaceholder"/>
                <form:input id="name" path="name" class="form-control" type="text" placeholder="${namePlaceholder}"
                            required="required"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a data-dismiss="modal" class="pull-left btn btn-default" href="${phaseUrl}"><i
                class="glyphicon glyphicon-arrow-left"></i> <spring:message code="back"/></a>
        <button type="submit" class="btn btn-success pull-right"><i class="glyphicon glyphicon-ok"></i>
            <c:choose> <c:when test="${not empty phase.id}"><spring:message
                    code="update"/></c:when><c:otherwise><spring:message code="add"/></c:otherwise></c:choose>
        </button>
    </div>
</form:form>

