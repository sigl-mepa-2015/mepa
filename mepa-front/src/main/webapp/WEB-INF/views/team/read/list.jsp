<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
	<div class="page-header">
		<h1>Toutes les équipes</h1>
	</div>
</div>

<div class="container">

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
<%-- <%@ include file="/WEB-INF/views/team/namespace.jsp"%>
<%@ include file="/WEB-INF/views/team/create/form.jsp"%>

<div class="container">
    <c:choose>
        <c:when test="${not empty teams}">
        <h2><spring:message code="team.listTitle"/></h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><spring:message code="name"/></th>
                        <th><spring:message code="edit"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${teams}" var="team" varStatus="loop">
                        <tr>
                            <td>${team.name}</td>
                            <td>
                                <%@ include file="/WEB-INF/views/team/remove/form.jsp"%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
            <h2><spring:message code="team.emptyTitle"/></h2>
    </c:otherwise>
    </c:choose>
</div> --%>