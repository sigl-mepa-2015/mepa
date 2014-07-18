<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
	<div class="page-header">
	<a class="pull-right btn btn-default" href="${pageContext.request.contextPath}/phase/view/${pool.phase.id}" title="Retour">
            <i class="glyphicon glyphicon-arrow-left"></i>
           Retour
        </a>
		<h1>Poule ${pool.name}</h1>
        <c:if test="${not empty pool.games}">
            <a href="https://twitter.com/intent/tweet" data-hashtags="${pool.phase.tournament.hashTag}" data-text="Poule ${pool.name} du tournoi ${pool.phase.tournament.name} phase ${pool.phase.name}" class="twitter-mention-button" data-lang="en">Tweeter les matchs</a>
            <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
        </c:if>
	</div>
</div>
<div>

    <c:if test="${not empty created}">
        <div id="message_box" class="alert alert-success">
            Poule bien crée.
            <!--spring:message code="pool.create.resultMessage" arguments="${created}"/-->
        </div>
    </c:if>

    <script>
        $("#message_box").delay(3000).slideUp();
    </script>
</div>
<div>
   <h3> Liste des équipes : </h3><br/>
        <table class="table table-striped">
    <thead>
    <tr>
        <th >Equipes</th>
    </tr>
    </thead>
            <tbody>
                <c:forEach items="${pool.teams}" var="t"  varStatus="loop">
                    <tr>
                        <td >${t.name}</td>
                    </tr>
               </c:forEach>
            </tbody>
        </table>
    <br/>
    <c:choose>
    <c:when test="${not empty pool.games}">

        <h3> Liste des matchs</h3>

            <table class="table table-striped">
            <thead>
            <tr>
                <th width="20%">Equipe 1</th>
                <th >Equipe 2</th>
                <th>Statut du match</th>
            </tr>
            </thead>
            <tbody>

                <c:forEach items="${pool.games}" var="g" varStatus="loop">
                    <tr>
                        <c:forEach items="${g.joinedGameTeams}" var="joined" varStatus="loop">
                            <td>${joined.team.name}</td>
                        </c:forEach>

                        <td><spring:message code="${g.status}" /></td>

                    </tr>
                </c:forEach>
        </tbody>
        </table>
        <a class="pull-left btn btn-default" href="${pageContext.request.contextPath}/afficherGame?poolID=${pool.id}" title="Saisie des matchs">
            Saisie des matchs
        </a>
        </c:when>
        <c:otherwise>
            <a class="pull-left btn btn-default" href="${pageContext.request.contextPath}/afficherGame?poolID=${pool.id}" title="Générer les matchs">
                Générer les matchs
            </a>
        </c:otherwise>
    </c:choose>

</div>
