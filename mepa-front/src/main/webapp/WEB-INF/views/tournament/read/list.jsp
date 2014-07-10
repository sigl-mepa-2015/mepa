<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">
	<div class="page-header">
		<h1>Tout les tournois</h1>
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
	

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Nom du tournoi</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tournaments}" var="t">
				<tr>
					<td>${t.name}</td>
					<td>
						<div class="btn-group btn-group-sm">
							<button type="button" class="btn btn-default">Left</button>
  							<button type="button" class="btn btn-default">Middle</button>
 							<button type="button" class="btn btn-default">Right</button>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$("#message_box").delay(1500).slideUp();
</script>
<%-- <%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<%@ include file="/WEB-INF/views/tournament/create/form.jsp"%>

<div class="container">
    <c:choose>
        <c:when test="${not empty tournaments}">
        <h2><spring:message code="tournament.listTitle"/></h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><spring:message code="name"/></th>
                        <th><spring:message code="delete"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tournaments}" var="tournament" varStatus="loop">
                        <tr>
                            <td>${tournament.name}</td>
                            <td>
                                <%@ include file="/WEB-INF/views/tournament/remove/form.jsp"%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
            <h2><spring:message code="tournament.emptyTitle"/></h2>
    </c:otherwise>
    </c:choose>
</div> --%>
