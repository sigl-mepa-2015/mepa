<%@ include file="/WEB-INF/views/includes/common.jsp" %>


<div>
    <div class="page-header">
        <c:if test="${fn:length(phaseView.teams) gt 1}">
            <a class="pull-right btn btn-success" title="Créer une poule"
           href="${pageContext.request.contextPath}/creerPoule?phaseID=${phaseView.id}">
            <i class="glyphicon glyphicon-plus"></i> Créer une poule
        </a>
            </c:if>
        <button type="button" class="pull-right btn btn-success"
                onClick="location.href = '${pageContext.request.contextPath}/team/form?phaseID=${phaseView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title2.nav1"/>
        </button>
        <button type="button" class="pull-right btn btn-success"
                onClick="location.href = '${pageContext.request.contextPath}/result/view?phaseID=${phaseView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title3.nav1"/>
        </button>
        <h1>Phase ${phaseView.name} </h1>
    </div>
</div>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="delete" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="team.removeTitle"/>
            </div>
            <div class="modal-body">
                <spring:message code="team.removeConfirmation"/>
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
    <c:if test="${not empty created}">
        <div class="alert alert-success fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <spring:message code="team.create.resultMessage" arguments="${created}"/>
        </div>
    </c:if>
    <c:if test="${not empty delete}">
        <div class="alert alert-success fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <spring:message code="team.remove.resultMessage" arguments="${delete}"/>
        </div>
    </c:if>
    <c:if test="${not empty update}">
        <div class="alert alert-success fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <spring:message code="team.update.resultMessage" arguments="${update}"/>
        </div>
    </c:if>
</div>
<div>
    <h2>Nombre maximum d'équipes : <c:if test="${phaseView.maxTeamNumber <0}">-</c:if>
        ${phaseView.maxTeamNumber}</h2><br/>

    <h2>Liste des équipes :</h2> <br/>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nom de l'équipe</th>
            <th>Phase</th>
            <th>Poule</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${phaseView.teams}" var="t">
            <tr>
                <td><a href="${pageContext.request.contextPath}/team/detail/${t.id}">${t.name}</a></td>
                <td>${t.phase.name}</td>
                <td><c:if test="${not empty t.pool}"><span class="glyphicon glyphicon-ok"></span></c:if></td>
                <td class="col-md-2">

                    <button type="button" class="btn btn-info"
                            onClick="location.href = '${pageContext.request.contextPath}/team/edit?teamID=${t.id}'">
                        <span class="glyphicon glyphicon-cog"></span>
                    </button>
                    <%@ include file="/WEB-INF/views/team/remove/form.jsp" %>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        $('#confirm-delete').on('show.bs.modal', function (e) {
            $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
        });
    </script>
    <br/>
    <h2>Liste des poules : </h2><br/>
    <c:choose>
        <c:when test="${empty phaseView.pools}">
            <div class="jumbotron">
                <div class="container">
                    <h2>Aucune poule crée</h2>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Nom de la Poule</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${phaseView.pools}" var="p">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/poolManager?poolID=${p.id}">${p.name}</a></td>
                        <td>
                            <button type="button" class="btn btn-primary"
                                    onClick="location.href='${pageContext.request.contextPath}/poolManager?poolID=${p.id}'">
                                <span class="glyphicon glyphicon-arrow-right"></span>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</div>
<script>
    $(function() {
        setTimeout(function() {
            $('.alert').hide();
        }, 3000);
    });
</script>
