<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="alert alert-success">
        A new tournament with name '${tournament.name}' has been added!
    </div>
    <p>
        <c:url var="createTournamentUrl" value="/tournament/create/"/>
        <a href="${createTournamentUrl}">
            <span class="glyphicon glyphicon-arrow-left"></span> Back to create tournament form
        </a>
    </p>
</div>
