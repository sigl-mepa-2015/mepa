<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Statistiques sur le tournoi</h1>
        <h4><spring:message code="tournament"/> : ${tournament.name}</h4>
    </div>
</div>

<div class="container" style="margin-bottom : 30px">
    <c:choose>
        <c:when test="${timeMoy != 0}">
            <h5><b>Estimation de l'heure de fin du tournoi :</b> ${endedDate}</h5>
            <h5><b>Durée moyenne des matchs :</b> ${timeMoy} min</h5>
        </c:when>
        <c:otherwise>
            <h5><b>Estimation de l'heure de fin du tournoi :</b> ---</h5>
            <h5><b>Durée moyenne des matchs :</b> ---</h5>
        </c:otherwise>
    </c:choose>

    <h5><b>Niveau de progression du tournoi</b></h5>
    <div class="progress">
        <div class="progress-bar progress-bar-todo progress-bar-striped" stylerole="progressbar" aria-valuenow="${endedGame}" aria-valuemin="0" aria-valuemax="${endedGame + comingGame}" style="width: ${(endedGame * 100) / (comingGame + endedGame + progressGame)}%;"><fmt:formatNumber value="${(endedGame * 100) / (comingGame + endedGame + progressGame)}" maxFractionDigits="0"/>%</div>
        <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="${endedGame}" aria-valuemin="0" aria-valuemax="${endedGame + comingGame}" style="width: ${(progressGame * 100) / (comingGame + endedGame + progressGame)}%;"><fmt:formatNumber value="${(progressGame * 100) / (comingGame + endedGame + progressGame)}" maxFractionDigits="0"/>%</div>
    </div>

    <h5><b>Niveau de progression des poules</b></h5>

    <div id="pieContainer" class="row">
        <c:forEach items="${mapPools}" var="pool">
            <div class="col-md-2 text-center">
                <div ><h5>${pool.key.name}</h5></div>
                <canvas id="pie${pool.key.id}" width="120" height="120" data-json="${fn:escapeXml(pool.value)}"></canvas>
            </div>
        </c:forEach>
    </div>
</div>


<div class="container">
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="active"><a href="#ended" role="tab" data-toggle="tab">Voir les matchs terminés</a></li>
        <li><a href="#coming" role="tab" data-toggle="tab">Voir les matchs a venir</a></li>
        <li><a href="#range" role="tab" data-toggle="tab">Voir le classement général des équipes</a></li>
        <li><a href="#rangePlayer" role="tab" data-toggle="tab">Voir le classement général des joueurs</a></li>
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
       							 <a data-toggle="collapse" data-parent="#accordion" href="#collapse2${pool.id}">
                                     <h4 class="panel-title">
          							    ${pool.name}
                                     </h4>
       							 </a>
	    					</div>
	    				<div id="collapse2${pool.id}" class="panel-collapse collapse">
	     				 <div class="panel-body">
	     			 		<div class="col-md-8 col-md-offset-2">
	     			 			<table class="table table-bordered">
									<tbody>
						     			 <c:forEach items="${pool.games}" var="game">
											<c:choose>
												<c:when test="${game.status == 'DONE'}">
													<tr class="scoreRow">
														<c:forEach items="${game.joinedGameTeams}" var="join" varStatus="i">
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
       							 <a data-toggle="collapse" data-parent="#accordion" href="#collapse${pool.id}">
                                     <h4 class="panel-title">
                                         ${pool.name}
                                     </h4>
       							 </a>
	    					</div>
	    				<div id="collapse${pool.id}" class="panel-collapse collapse">
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
	  		<table class="table table-striped table-bordered" id="rangeTable">
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
        <div class="tab-pane" id="rangePlayer">
            <h3>Classement général des joueurs pour le tournoi</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Classement</th>
                    <th>Joueur</th>
                    <th>Equipe</th>
                    <th>Point</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listOrderPlayer}" var="p" varStatus="index">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${index.index != 0
		  							&& p.nbPoint == listOrderPlayer[index.index - 1].nbPoint}">
                                    -
                                </c:when>
                                <c:otherwise>${index.index + 1}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${p.name}</td>
                        <td>${p.team.name}</td>
                        <td class="info">${p.nbPoint}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/mepa_chart.js"></script>
