<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Créer une poule</h1>
	</div>
</div>

<div class="container">
        <form:form role="form"  modelAttribute="createPoolFormBean" method="POST">
            <label class="col-lg-3">Nom de la poule</label>
            <input type="HIDDEN" name="tournamentID" value="${tournamentID}">
            <input type="text" class="col-lg-3" name="name" required="required"><br/>
            </br>
            <table class="table table-striped table-bordered" id="rangeTable">
                <thead>
                    <tr>
                        <th>Equipe</th>
                        <th>Selection</th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${teams}" var="t" varStatus="loop">
                            <tr>
                                <td>${t.name}</td>
                                 <td><input type="checkbox" name='teams' value="${t.id}"/></td>
                            <tr>
                     </c:forEach>
                </tbody>
            </table>
            <button type="submit"  class="btn btn-primary pull-right">Créer</button>
        </form:form>
    <a class="pull-left btn btn-default" href="/mepa-front/tournament/view/${tournamentID}" title="Annuler">
        <i class="glyphicon glyphicon-arrow-left"></i>
        Annuler
    </a>

</div>
</div>


