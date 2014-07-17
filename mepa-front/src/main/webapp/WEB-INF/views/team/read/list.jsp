<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div>
	<div class="page-header">
		<h1>Toutes les équipes</h1>
	</div>
</div>

<div>

	<c:choose>
		<c:when test="${team != null}">
			<div id="message_box" class="alert alert-success">
      		  <spring:message code="team.create.resultMessage" arguments="${team.name}" />
    		</div>
		</c:when>
	</c:choose>
	

<div class="col-md-9 col-md-offset-1">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Nom de l'équipe</th>
                <th>Tournoi</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${teams}" var="t">
				<tr>
					<td width="70%">${t.name}</td>
                    <td width="70%">${t.tournament.name}</td>
					<td width="30%">
						<button type="button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath}/team/edit?teamID=${t.id}'">
							<span class="glyphicon glyphicon-cog"></span>
						</button>
		
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
