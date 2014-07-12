<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Statistiques sur le tournoi</h1>
		<h4><spring:message code="tournament"/> : ${tournament.name}</h4>
	</div>
</div>

<div class="container">
	<h5>Niveau de progression du tournoi : ${(endedGame * 100) / (comingGame + endedGame)} %</h5>
	<div class="progress">
  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="${endedGame}" aria-valuemin="0" aria-valuemax="${endedGame + comingGame}" style="width: ${(endedGame * 100) / (comingGame + endedGame)}%;">
  </div>
</div>
</div>


<div class="container">
	<ul class="nav nav-tabs nav-justified" role="tablist">
		<li class="active"><a href="#ended" role="tab" data-toggle="tab">Voir les matchs termines</a></li>
		<li><a href="#coming" role="tab" data-toggle="tab">Voir les matchs a venir</a></li>
		<li><a href="#range" role="tab" data-toggle="tab">Voir le classement general</a></li>
	</ul>
</div>

<div class="container">
	<div class="tab-content">
	  <div class="tab-pane active" id="ended">
	  	<h3>Liste des matchs termines</h3>
		  <c:forEach items="${tournament.pools}" var="pool">
			<h3>Poule : ${pool.name}</h3>
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${pool.games}" var="game">
						<c:choose>
							<c:when test="${game.status == 'DONE'}">
								<tr>
									<c:forEach items="${game.joinedGameTeams}" var="join">
										<td>${join.team.name}</td>
										<td>${join.score}</td>
									</c:forEach>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
	  </div>
	  <div class="tab-pane" id="coming">
	  		<h3>Liste des matchs a venir</h3>
	  		<c:forEach items="${tournament.pools}" var="pool">
			<h3>Poule : ${pool.name}</h3>
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${pool.games}" var="game">	
						<c:choose>
							<c:when test="${game.status == 'TODO'}">
								<tr>
									<c:forEach items="${game.joinedGameTeams}" var="join">
										<td>${join.team.name}</td>
									</c:forEach>
								</tr>	
							</c:when>	
						</c:choose>
					</c:forEach> 
				</tbody>
			</table>
		</c:forEach>
	  </div>
	  <div class="tab-pane" id="range">
	  		<h3>Classement general pour le tournoi</h3>
	  		<table class="table table-bordered">
	  			<thead>
	  				<tr>
	  					<th>Classement</th>
	  					<th>Equipe</th>
	  					<th>Poule</th>
	  					<th>J</th>
	  					<th>V</th>
	  					<th>N</th>
	  					<th>D</th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<tr>
	  					<c:forEach items="${orderTeams}" var="t" varStatus="index">
	  						<td>
	  							<c:choose>
		  							<c:when test="${index != 0 && t.winGame == orderTeams[index - 1].winGame && t.drawGame == orderTeams[index - 1].drawGame && t.loseGame == orderTeams[index - 1].loseGame}">
		  								-
		  							</c:when>
		  							<c:otherwise>${index + 1}</c:otherwise>
	  							</c:choose>
	  						</td>
	  						<td>${t.name}</td>
	  						<td>${t.pool.name}</td>
	  						<td class="info">${t.winGame + t.drawGame + t.loseGame}</td>
	  						<td class="success">${t.winGame}</td>
	  						<td class="warning">${t.drawGame}</td>
	  						<td class="danger">${t.loseGame}</td>
	  					</c:forEach>
	  				</tr>
	  			</tbody>
	  		</table>
	  </div>
	</div>
</div>