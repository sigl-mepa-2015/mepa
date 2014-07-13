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
		<br>
		<h5>Nombre de matchs joues</h5>
	</div>
	<div class="col-md-6">
		shdjksfhj
	</div>
</div>