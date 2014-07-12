<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Statistiques sur le tournoi</h1>
		<h4><spring:message code="tournament"/> : ${tournament.name}</h4>
	</div>
</div>

<div class="container">
	<h5>Niveau de progression du tournoi</h5>
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
		  			<div class="panel-group" id="accordion">
	 					 <div class="panel panel-default">
	   						 <div class="panel-heading">
	      						<h4 class="panel-title">
       							 <a data-toggle="collapse" data-parent="#accordion" href="#collapse2${pool.id}">
          							Poule : ${pool.name}
       							 </a>
	     				 	  </h4>
	    					</div>
	    				<div id="collapse2${pool.id}" class="panel-collapse collapse in">
	     				 <div class="panel-body">
	     			 		<div class="col-md-8 col-md-offset-2">
	     			 			<table class="table table-bordered">
									<tbody>
						     			 <c:forEach items="${pool.games}" var="game">
											<c:choose>
												<c:when test="${game.status == 'DONE'}">
													<tr>
														<c:forEach items="${game.joinedGameTeams}" var="join" varStatus="i">
															<c:choose>
																<c:when test="${i.index % 2 == 0}">
																	<td style="text-align : center">${join.team.name}</td>
																	<td style="text-align : center">${join.score}</td>
																</c:when>
																<c:otherwise>
																	<td style="text-align : center">${join.score}</td>
																	<td style="text-align : center">${join.team.name}</td>
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
	    			</div>
	    		</div>
  			</div>
  		</c:forEach>
	  </div>
	  <div class="tab-pane" id="coming">
	  		<h3>Liste des matchs a venir</h3>
	  		<c:forEach items="${tournament.pools}" var="pool">
			<div class="panel-group" id="accordion">
	 					 <div class="panel panel-default">
	   						 <div class="panel-heading">
	      						<h4 class="panel-title">
       							 <a data-toggle="collapse" data-parent="#accordion" href="#collapse${pool.id}">
          							Poule : ${pool.name}
       							 </a>
	     				 	  </h4>
	    					</div>
	    				<div id="collapse${pool.id}" class="panel-collapse collapse in">
	     				 <div class="panel-body">
	     			 		<div class="col-md-8 col-md-offset-2">
	     			 			<table class="table table-bordered">
									<tbody>
						     			 <c:forEach items="${pool.games}" var="game">
											<c:choose>
												<c:when test="${game.status == 'TODO'}">
													<tr>
														<c:forEach items="${game.joinedGameTeams}" var="join" varStatus="i">
																	<td style="text-align : center">${join.team.name}</td>
														</c:forEach>
													</tr>
												</c:when>
											</c:choose>
										</c:forEach>
									</tbody>
								</table>
							</div>
	        	    	</div>
	    			</div>
	    		</div>
  			</div>
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
	  					<c:forEach items="${listOrderTeam}" var="t" varStatus="index">
                        <tr>
	  						<td>
	  							<c:choose>
		  							<c:when test="${index.index != 0
		  							&& t.winGame == listOrderTeam[index.index - 1].winGame
		  							&& t.drawGame == listOrderTeam[index.index - 1].drawGame
		  							&& t.loseGame == listOrderTeam[index.index - 1].loseGame}">
                                        -
		  							</c:when>
		  							<c:otherwise>${index.index + 1}</c:otherwise>
	  							</c:choose>
                                <%--<c:out value="${index.index}"/>--%>
	  						</td>
	  						<td>${t.name}</td>
	  						<td>${t.pool.name}</td>
	  						<td class="info">${t.winGame + t.drawGame + t.loseGame}</td>
	  						<td class="success">${t.winGame}</td>
	  						<td class="warning">${t.drawGame}</td>
	  						<td class="danger">${t.loseGame}</td>
                        </tr>
	  					</c:forEach>
	  			</tbody>
	  		</table>
	  </div>
	</div>
</div>