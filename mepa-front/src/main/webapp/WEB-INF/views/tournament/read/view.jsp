<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div>
    <div class="page-header">
        <a class="pull-right btn btn-success" title="Créer une poule" href="${pageContext.request.contextPath}/creerPoule?tournamentID=${tournamentView.id}">
            <i class="glyphicon glyphicon-plus"></i> Créer une poule
        </a>
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/team/form?tournamentID=${tournamentView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title2.nav1"/>
        </button>
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/result/view?tournamentID=${tournamentView.id}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="home.bar.title3.nav1"/>
        </button>
        <h1>Tournoi ${tournamentView.name} </h1>
    </div>
</div>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="delete" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="team.removeTitle" />
            </div>
            <div class="modal-body">
                <spring:message code="team.removeConfirmation" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                <a href="#" class="btn btn-danger danger"><spring:message code="delete" /></a>
            </div>
        </div>
    </div>
</div>
<div>
    <h2>Maximum d'équipes allouables : <c:if test="${tournamentView.maxTeamNumber <0}">-</c:if>${tournamentView.maxTeamNumber}</h2><br/>
    <h2>Liste des équipes :</h2> <br/>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nom de l'équipe</th>
                <th>Tournoi</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tournamentView.teams}" var="t">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/team/detail/${t.id}">${t.name}</a></td>
                    <td>${t.tournament.name}</td>
                    <td  class="col-md-2">
                        <button type="button" class="btn btn-info" onClick="location.href = '${pageContext.request.contextPath}/team/edit?teamID=${t.id}'">
                            <span class="glyphicon glyphicon-cog"></span>
                        </button>
                        <%@ include file="/WEB-INF/views/team/remove/form.jsp" %>
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
    <br/>
    <h2>Liste des poules : </h2><br/>
    <c:choose>
        <c:when test="${empty tournamentView.pools}">
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
                <c:forEach items="${tournamentView.pools}" var="p">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/poolManager?poolID=${p.id}">${p.name}</a></td>
                        <td>
                            <button type="button" class="btn btn-primary" onClick="location.href='${pageContext.request.contextPath}/poolManager?poolID=${p.id}'">
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
