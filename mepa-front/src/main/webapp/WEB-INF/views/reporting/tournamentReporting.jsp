<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Statistiques sur le tournoi</h1>
		<h3><spring:message code="tournament"/> : ${t.name}</h3>
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
	<%-- <c:forEach items="${t.pools}" var="pool">
			<h3>Poule : ${pool.name}</h3>
			<table class="table table-bordered">
				<tbody>
					<c:forEach items="${pool.matchs}" var="match">	
						<tr>
							<td>${match.team1.name}</td>
							<td>${match.scoreTeam1}</td>
							<td>${match.scoreTeam2}</td>
							<td>${match.team2.name}</td>
						</tr>		
					</c:forEach>
				</tbody>
			</table>
		</c:forEach> --%>
	  </div>
	  <div class="tab-pane" id="coming">
	  		Coming pane
	  </div>
	  <div class="tab-pane" id="range">
	  		Range
	  </div>
	</div>
</div>