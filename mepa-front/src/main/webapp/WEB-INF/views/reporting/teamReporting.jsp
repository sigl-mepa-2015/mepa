<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Statistique d'equipe</h1>
		<h4>Equipe : ${team.name}</h4>
		<h5><spring:message code="tournament"/> : ${team.tournament.name}</h5>
		<h5>Poule : ${team.pool.name}</h5>
	</div>
</div>

<div class="container">
	<h4>Statistiques classements</h4>
	<div class="col-md-6">
		<h5>Classement global</h5>
		<table class="table table-bordered">
			<tbody>
				<c:choose>
					<c:when test="${globalPrev != null}">
					<tr>
						<td>${rangeGlobal - 1}</td>
						<td>${globalPrev.name}</td>
					</tr>
					</c:when>
				</c:choose>
				<tr>
					<td>${rangeGlobal}</td>
					<td>${team.name}</td>
				</tr>
				<c:choose>
					<c:when test="${globalNext != null}">
					<tr>
						<td>${rangeNext + 1}</td>
						<td>${globalNext.name}</td>
					</tr>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		</div>
		<div class="col-md-6">
		<h5>Classement poule</h5>
		<table class="table table-bordered">
			<tbody>
				<c:choose>
					<c:when test="${poolPrev != null}">
					<tr>
						<td>${rangePool - 1}</td>
						<td>${poolPrev.name}</td>
					</tr>
					</c:when>
				</c:choose>
				<tr>
					<td>${rangePool}</td>
					<td>${team.name}</td>
				</tr>
				<c:choose>
					<c:when test="${poolPrev != null}">
					<tr>
						<td>${rangePool + 1}</td>
						<td>${poolPrev.name}</td>
					</tr>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>
<div class="container">
	<h4>Statistiques matchs</h4>
	<div class="col-md-4">
		<h5>Nombre de match joues : ${endedGame}</h5>
		<h5>Nombre de match restants : ${todoGame}</h5>
		<h5>Duree moyenne match : ${averrageTime}</h5>
		<h5>Temps de jeu total : ${averrageTime * endedGame}</h5>
		<h5>Niveau de progression dans le tournoi</h5>
	    <div class="progress">
	        <div class="progress-bar progress-bar-todo progress-bar-striped" stylerole="progressbar" aria-valuenow="${endedGame}" aria-valuemin="0" aria-valuemax="100" style="width: ${(endedGame * 100) / (endedGame +todoGame)}%;"><fmt:formatNumber value="${(endedGame * 100) / (endedGame +todoGame)}" maxFractionDigits="0"/>%</div>
    	</div>
	</div>
	<div class="col-md-4 text-center">
		<div><h5>Repartition resultat</h5></div>
		<canvas id="pieGame" width="180" height="180" data-json="${fn:escapeXml(jsonResult)}"></canvas>
	</div>
	<div class="col-md-4 text-center">
		<div><h5>Repartition score</h5></div>
		<canvas id="pieGame2" width="180" height="180" data-json=""></canvas>
	</div>
</div>
</br>

<div class="container">
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="active"><a href="#ended" role="tab" data-toggle="tab">Voir les matchs termines</a></li>
        <li><a href="#coming" role="tab" data-toggle="tab">Voir les matchs a venir</a></li>
        <li><a href="#player" role="tab" data-toggle="tab">Voir les joueurs</a></li>
    </ul>
</div>

<div class="container">
	<div class="tab-content">
		<div class="tab-pane active" id="ended">
			<h3>Liste des matchs termines</h3>
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-bordered">
					<tbody>
		     			  <c:forEach items="${teamGame}" var="g">
							 <c:choose>
								<c:when test="${g.status == 'DONE'}">
									<tr class="scoreRow">
										<c:forEach items="${g.joinedGameTeams}" var="join" varStatus="i">
											<c:choose>
												<c:when test="${i.index % 2 == 0}">
													<td class="text-center">${join.team.name}</td>
													<td class="text-center score1">${join.score}</td>
												</c:when>
												<c:otherwise>
													<td class="text-center score2">${join.score}</td>
													<td class="text-center">${join.team.name}</td>
												</c:otherwise>
											</c:choose>	
										</c:forEach>
									</tr>
								</c:when>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane" id="coming">
			<h3>Liste des matchs a venir</h3>
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-bordered">
					<tbody>
						<c:forEach items="${teamGame}" var="g">
							 <c:choose>
								<c:when test="${g.status == 'TODO'}">
									<tr class="scoreRow">
										<c:forEach items="${g.joinedGameTeams}" var="join" varStatus="i">
											<td class="text-center">${join.team.name}</td>
										</c:forEach>
									</tr>
								</c:when>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="tab-pane" id="player">
             <h3>Liste des joueurs de l'equipe</h3>
            <table class="table table-bordered table-striped" id="playersTable">
                <thead>
	                <tr>
	                    <th>Joueur</th>
	                    <th>Points</th>
	                </tr>
                </thead>
                <tbody>
                <c:forEach items="${players}" var="p">
                    <tr>
                        <td>${p.name}</td>
                        <td class="info">${p.nbPoint}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/mepa_chart.js"></script>

