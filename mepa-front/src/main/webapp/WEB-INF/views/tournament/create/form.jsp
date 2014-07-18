<%--
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp" %>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only"><spring:message code="close"/></span></button>
        <h1><c:choose>
            <c:when test="${not empty tournament.id}">
                <spring:message code="tournament.updateTitle"/>
            </c:when>
            <c:otherwise>
                <spring:message code="tournament.createTitle"/>
            </c:otherwise>
        </c:choose></h1>
    </div>
    <form:form role="form" action="${addTournamentFormActionUrl}" cssClass="row" cssStyle="margin:auto;"
               modelAttribute="tournament" method="post">
        <div class="modal-body">
            <div class="form-group">
                <form:input id="id" path="id" type="hidden"/>
                <div class="input-group col-md-4">
                    <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
                    <label for="name"><spring:message code="tournament.form.nameLabel"/></label>
                    <spring:message code='tournament.form.namePlaceholder' var="namePlaceholder"/>
                    <form:input id="name" path="name" class="form-control" type="text" placeholder="${namePlaceholder}"
                                required="required"/>
                </div>
                <div class="input-group col-md-4">
                    <form:errors path="maxTeamNumber" cssStyle="color: #FF0000;" htmlEscape="false"/>
                    <label for="maxTeamNumber"><spring:message code="tournament.form.maxTeamNumberLabel"/></label>
                    <spring:message code='tournament.form.maxTeamNumberPlaceholder' var="maxTeamNumberPlaceholder"/>
                    <form:input id="maxTeamNumber" path="maxTeamNumber" class="form-control" min="0" type="number"
                                placeholder="${maxTeamNumberPlaceholder}"/>
                </div>
                <div class="input-group col-md-4">
                    <form:errors path="maxPlayerNumber" cssStyle="color: #FF0000;" htmlEscape="false"/>
                    <label for="maxPlayerNumber"><spring:message code="tournament.form.maxPlayerNumberLabel"/></label>
                    <spring:message code='tournament.form.maxPlayerNumberPlaceholder' var="maxPlayerNumberPlaceholder"/>
                    <form:input id="maxPlayerNumber" path="maxPlayerNumber" class="form-control" min="0" type="number"
                                placeholder="${maxPlayerNumberPlaceholder}"/>
                </div>
                <div class="input-group col-md-4">
                    <form:errors path="type" cssStyle="color: #FF0000;" htmlEscape="false"/>
                    <label for="type"><spring:message code="tournament.form.typeLabel"/></label>
                    <spring:message code='tournament.form.typePlaceholder' var="typePlaceholder"/>
                    <form:input id="type" path="type" class="form-control" type="text"
                                placeholder="${typePlaceholder}"/>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a data-dismiss="modal" class="pull-left btn btn-default" href="${tournamentUrl}"><i
                    class="glyphicon glyphicon-arrow-left"></i> <spring:message code="back"/></a>
            <button type="submit" class="btn btn-success pull-right"><i class="glyphicon glyphicon-ok"></i>
                <c:choose> <c:when test="${not empty tournament.id}"><spring:message
                        code="update"/></c:when><c:otherwise><spring:message code="add"/></c:otherwise></c:choose>
            </button>
        </div>
    </form:form>

