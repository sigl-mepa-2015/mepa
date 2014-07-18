<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp" %>

<div class="modal fade" id="addTournamentForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <%@ include file="/WEB-INF/views/tournament/create/form.jsp" %>
        </div>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="updateTournamentForm">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="createPhaseForm">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade" id="updatePhaseForm">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade confirm-delete" id="removeTournamentForm" tabindex="-1" role="dialog" aria-labelledby="delete"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="tournament.removeTitle"/>
            </div>
            <div class="modal-body">
                <spring:message code="tournament.removeConfirmation"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="cancel"/></button>
                <a href="#" class="btn btn-danger danger"><spring:message code="delete"/></a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade confirm-delete" id="removePhaseForm" tabindex="-1" role="dialog" aria-labelledby="delete"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="phase.removeTitle"/>
            </div>
            <div class="modal-body">
                <spring:message code="phase.removeConfirmation"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="cancel"/></button>
                <a href="#" class="btn btn-danger danger"><spring:message code="delete"/></a>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="page-header">
        <spring:message code="create" var="addLabel"/>
        <sec:authorize access="isAuthenticated()">
            <a data-toggle="modal" data-target="#addTournamentForm" class="pull-right btn btn-success"
               title="${addLabel}"><i
                    class="glyphicon glyphicon-plus"></i> <spring:message code="tournament.create"/></a>
        </sec:authorize>
        <h1>Tous les tournois</h1>
    </div>
</div>

<div>
    <c:if test="${not empty removed}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="tournament.remove.resultMessage" arguments="${removed}"/>
        </div>
    </c:if>
    <c:if test="${not empty updated}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="tournament.update.resultMessage" arguments="${updated}"/>
        </div>
    </c:if>
    <c:if test="${not empty created}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="tournament.create.resultMessage" arguments="${created}"/>
        </div>
    </c:if>
    <c:if test="${not empty unknown}">
        <div id="message_box" class="alert alert-warning">
            <spring:message code="tournament.unknownMessage" arguments="${unknown}"/>
        </div>
    </c:if>
    <c:if test="${not empty removedPhase}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="phase.remove.resultMessage" arguments="${removedPhase}"/>
        </div>
    </c:if>
    <c:if test="${not empty updatedPhase}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="phase.update.resultMessage" arguments="${updatedPhase}"/>
        </div>
    </c:if>
    <c:if test="${not empty createdPhase}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="phase.create.resultMessage" arguments="${createdPhase}"/>
        </div>
    </c:if>
    <c:if test="${not empty unknownPhase}">
        <div id="message_box" class="alert alert-warning">
            <spring:message code="phase.unknownMessage" arguments="${unknownPhase}"/>
        </div>
    </c:if>
    <script>
        $("#message_box").delay(1500).slideUp();
    </script>

    <div class="col-md-11 col-md-offset-1" style="text-align: center">
        <c:choose>
            <c:when test="${not empty tournaments}">
                <table class="table table-striped" style="text-align: center">
                    <thead style="text-align: center">
                    <tr>
                        <th style="text-align: center"><spring:message code="name"/></th>
                        <th style="text-align: center"><spring:message code="type"/></th>
                        <th style="text-align: center"><spring:message code="maxTeamNumber"/></th>
                        <th style="text-align: center"><spring:message code="maxPlayerNumber"/></th>
                        <th style="text-align: center"><spring:message code="actions"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tournaments}" var="t">
                        <tr class="">
                            <td>${t.name}</td>
                            <td>${t.type}</td>
                            <td>${t.maxTeamNumber}</td>
                            <td>${t.maxPlayerNumber}</td>
                            <td class="col-md-2">
                                <sec:authorize access="isAuthenticated()">
                                    <spring:message code="update" var="updateLabel"/>
                                    <a data-toggle="modal" data-target="#updateTournamentForm"
                                       href="${pageContext.request.contextPath}/tournament/form/${t.id}"
                                       title="${updateLabel}" class="btn btn-sm btn-default">
                                        <span class="glyphicon glyphicon-cog"></span>
                                    </a>
                                    <%@ include file="/WEB-INF/views/tournament/remove/form.jsp" %>
                                    <spring:message code="create" var="createLabel"/>
                                    <a data-toggle="modal" data-target="#createPhaseForm"
                                       href="${pageContext.request.contextPath}/phase/form/${t.id}/"
                                       title="${createLabel}" class="btn btn-success">
                                        <span class="glyphicon glyphicon-plus-sign"></span>
                                    </a>
                                </sec:authorize>
                            </td>
                        </tr>
                        <c:forEach items="${t.phases}" var="p">
                            <tr class="phase info">
                                <td><i class="glyphicon glyphicon-chevron-right"></i>&nbsp;${p.name}</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td class="col-md-3">
                                    <sec:authorize access="isAuthenticated()">
                                        <button type="button" class="btn btn-primary"
                                                onClick="location.href='${pageContext.request.contextPath}/phase/view/${p.id}'">
                                            <span class="glyphicon glyphicon-arrow-right"></span>
                                        </button>
                                        <button type="button" class="btn btn-info"
                                                onClick="location.href='${pageContext.request.contextPath}/reporting/tournament?phaseID=${p.id}'">
                                            <span class="glyphicon glyphicon-stats"></span>
                                        </button>
                                        <spring:message code="update" var="updateLabel"/>
                                        <a data-toggle="modal" data-target="#updatePhaseForm"
                                           href="${pageContext.request.contextPath}/phase/form/${t.id}/${p.id}"
                                           title="${updateLabel}" class="btn btn-default">
                                            <span class="glyphicon glyphicon-cog"></span>
                                        </a>
                                        <%@ include file="/WEB-INF/views/phase/remove/form.jsp" %>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
                <script>
                    $('.confirm-delete').on('show.bs.modal', function (e) {
                        $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
                    });
                </script>
            </c:when>
            <c:otherwise>
                <div class="jumbotron">
                    <div class="container">
                        <h2><spring:message code="tournament.emptyTitle"/></h2>
                        <sec:authorize access="isAuthenticated()">
                            <a data-toggle="modal" data-target="#addTournamentForm" class="btn-lg btn btn-primary"
                               title="${addLabel}"><i
                                    class="glyphicon glyphicon-plus"></i> <spring:message code="tournament.create"/></a>
                        </sec:authorize>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
