<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
	<div class="page-header">
		<h1>Tous les tournois</h1>
	</div>
</div>

<div class="container">

	<c:choose>
		<c:when test="${tournament != null}">
			<div id="message_box" class="alert alert-success">
      		  <spring:message code="tournament.create.resultMessage" arguments="${tournament.name}" />
    		</div>
		</c:when>
	</c:choose>
	

<div class="col-md-9 col-md-offset-1">
	<table class="table table-striped" style="text-align: center">
		<thead>
			<tr>
                <th>Nom du tournoi</th>
                <th>Type de tournoi</th>
                <th>Nombre maximum d'équipes</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tournaments}" var="t">
				<tr>
					<td >${t.name}</td>
                    <td >${t.type}</td>
                    <td >${t.maxTeamNumber}</td>
					<td class="col-md-4">
						<button type="button" class="btn btn-primary" >
							<span class="glyphicon glyphicon-arrow-right"></span>
						</button>
						<button type="button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath}/reporting/tournament?tournamentID=${t.id}'">
							<span class="glyphicon glyphicon-stats"></span>
						</button>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-cog"></span>
						</button>
                        <%@ include file="/WEB-INF/views/tournament/remove/form.jsp"%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>

<script>
	$("#message_box").delay(1500).slideUp();
</script>