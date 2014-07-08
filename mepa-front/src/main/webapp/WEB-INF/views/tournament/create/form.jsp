<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="page-header">
        <h1>Tournament creation</h1>
    </div>
    <c:url var="addTournamentFormActionUrl" value="/tournament/create/add"/>
    <form:form role="form" action="${addTournamentFormActionUrl}" modelAttribute="addTournamentFormBean" method="post">
        <div class="form-group">
            <form:errors path="name" cssStyle="color: #FF0000;" htmlEscape="false"/>
            <p class="help-block">This is the name of the tournament</p>
            <label for="name">Enter a name below:</label>
            <br/>
            <form:input id="name" path="name" type="text" maxlength="20" placeholder="Tournament name"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>

<div class="container">
    <h2>Tournament in database</h2>
    <div class="alert alert-warning">
        A new random tournament will be added each time this page is called
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Created</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tournaments}" var="tournament" varStatus="loop">
                    <tr>
                        <td>${tournament.name}</td>
                        <td><fmt:formatDate value="${tournament.created}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
