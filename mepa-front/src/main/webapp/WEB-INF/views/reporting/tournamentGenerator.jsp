<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Generateur de tournoi</h1>
    </div>
</div>

<div class="container">
	 <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="active"><a href="#generate" role="tab" data-toggle="tab">Generer un tournoi</a></li>
        <li><a href="#play" role="tab" data-toggle="tab">Jouer un tournoi</a></li>
    </ul>
</div>

<div class="container">
	<div class="tab-content">
		<div class="tab-pane active" id="generate">
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/injectData/createTournament" method="POST">
					<div class="form-group">
						<label for="tournamentName">Nom du tournoi</label>
						<input name="tournamentName" class="form-control"></input>
					</div>
					<div class="form-group">
						<label for="poolNumber">Nombre de poules</label>
						<input name="poolNumber" class="form-control" type="number" min="1"></input>
					</div>
					<div class="form-group">
						<label for="teamNumber">Nombre d'equipes par poule</label>
						<input name="teamNumber" class="form-control" type="number" min="1"></input>
					</div>
					<div class="form-group">
						<label for="playerNumber">Nombre de joueurs par equipe</label>
						<input name="playerNumber" class="form-control" type="number" min="1"></input>
					</div>
					<div class="checkbox">
					    <label>
					      <input name="return" type="checkbox"> Match Aller-Retour
					    </label>
 				  </div>
					 <button type="submit" class="btn btn-primary">
						 <span class="glyphicon glyphicon-flash"></span>Generer
					</button>
				</form>
			</div>
		</div>
		<div class="tab-pane" id="play">
			<form action="${pageContext.request.contextPath}/injectData/playGame" method="POST">
				<div class="col-md-4">
					<div class="form-group">
						<label for="tournamentId">Selectionner un tournoi</label>
						<select name="tournamentId" class="form-control">
							<c:forEach items="${allTournament}" var="t">
								<option value="${t.id}">${t.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="pourPlaying">% max de match a joue</label>
						<input name="pourPlaying" class="form-control" type="number" min="0" max="100"></input>
					</div>
					<div class="form-group">
						<label for="currentPlaying">% max de match en cours</label>
						<input name="currentPlaying" class="form-control" type="number" min="0" max="100"></input>
					</div>
					<div class="form-group">
						<label for="minGame">duree min match</label>
						<input name="minGame" class="form-control" type="number" min="0"></input>
					</div>
					<div class="form-group">
						<label for="maxGame">duree max match</label>
						<input name="maxGame" class="form-control" type="number" min="0"></input>
					</div>
					<div class="form-group">
						<label for="scoreMax">score maximum</label>
						<input name="scoreMax" class="form-control" type="number" min="0"></input>
					</div>
					<button type="submit" class="btn btn-primary">
						 <span class="glyphicon glyphicon-play"></span>Jouer
					</button>
				</div>
			</form>
		</div>
	</div>
</div>