<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1><spring:message code="endedGame.title"/></h1>
		<h3><spring:message code="tournament"/> : ${t.name}</h3>
	</div>
</div>

<div class="container">

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