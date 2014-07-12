<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp" %>

<div class="modal fade" id="addTournamentForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <%@ include file="/WEB-INF/views/tournament/create/form.jsp" %>
        </div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="updateTournamentForm">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="delete" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="tournament.removeTitle" />
            </div>
            <div class="modal-body">
                <spring:message code="tournament.removeConfirmation" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                <a href="#" class="btn btn-danger danger"><spring:message code="delete" /></a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="page-header">
        <spring:message code="create" var="addLabel"/>
        <a data-toggle="modal" data-target="#addTournamentForm" class="pull-right btn btn-success" title="${addLabel}"><i
                class="glyphicon glyphicon-plus"></i> <spring:message code="create"/></a>

        <h1>Tous les tournois</h1>
    </div>
</div>

<div class="container">
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
    <script>
        $("#message_box").delay(1500).slideUp();
    </script>

    <div class="col-md-9 col-md-offset-1">
        <c:choose>
            <c:when test="${not empty tournaments}">
                <table class="table table-striped" style="text-align: center">
                    <thead style="text-align: center">
                    <tr>
                        <th>Nom</th>
                        <th>Type</th>
                        <th>Nombre d'équipes maximum</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tournaments}" var="t">
                        <tr>
                            <td>${t.name}</td>
                            <td>${t.type}</td>
                            <td>${t.maxTeamNumber}</td>
                            <td class="col-md-4">
                                <button type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-arrow-right"></span>
                                </button>
                                <button type="button" class="btn btn-info"
                                        onClick="location.href='${pageContext.request.contextPath}/reporting/tournament?tournamentID=${t.id}'">
                                    <span class="glyphicon glyphicon-stats"></span>
                                </button>
                                <spring:message code="update" var="updateLabel"/>
                                <a data-toggle="modal" data-target="#updateTournamentForm" href="${pageContext.request.contextPath}/tournament/form/${t.id}"
                                   title="${updateLabel}" class="btn btn-default">
                                    <span class="glyphicon glyphicon-cog"></span>
                                </a>
                                <%@ include file="/WEB-INF/views/tournament/remove/form.jsp" %>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
                <script>

                    $('#confirm-delete').on('show.bs.modal', function(e) {
                        $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
                    });
                </script>
            </c:when>
            <c:otherwise>
                <div class="jumbotron">
                    <div class="container">
                        <h2><spring:message code="tournament.emptyTitle"/></h2>
                        <a data-toggle="modal" data-target="#addTournamentForm" class="btn-lg btn btn-primary" title="${addLabel}"><i
                                class="glyphicon glyphicon-plus"></i> <spring:message code="create"/></a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
