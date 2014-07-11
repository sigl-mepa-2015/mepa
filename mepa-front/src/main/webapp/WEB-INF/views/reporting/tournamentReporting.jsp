<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Statistiques sur le tournoi</h1>
		<h4><spring:message code="tournament"/> : ${t.name}</h4>
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
		  <c:forEach items="${t.pools}" var="pool">
			<h3>Poule : ${pool.name}</h3>
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${pool.games}" var="game">	
						<c:choose>
							<c:when test="${game.status == 'ENDED'}">
								<tr>
									<td>${game.team1.name}</td>
									<td>${game.resultTeam1}</td>
									<td>${game.resultTeam2}</td>
									<td>${game.team2.name}</td>
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
	  		<%--  <c:forEach items="${t.pools}" var="pool">
			<h3>Poule : ${pool.name}</h3>
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${pool.games}" var="game">	
						<c:choose>
							<c:when test="${game.status == 'TODO'}">
								<tr>
									<td>${game.team1.name}</td>
									<td>${game.resultTeam1}</td>
									<td>${game.resultTeam2}</td>
									<td>${game.team2.name}</td>
								</tr>	
							</c:when>	
						</c:choose>
					</c:forEach> 
				</tbody>
			</table>
		</c:forEach>--%>
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
	  					<th>D</th>
	  					<th>N</th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<tr>
	  					<td>1</td>
	  					<td>Equipe1</td>
	  					<td>Poule A</td>
	  					<td class="info">5</td>
	  					<td class="success">3</td>
	  					<td class="danger">1</td>
	  					<td class="warning">4</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  </div>
	</div>
</div>