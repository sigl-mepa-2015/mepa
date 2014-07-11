<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Créer une poule</h1>
	</div>
</div>

<div class="container">
        <form:form role="form"  modelAttribute="createPoolFormBean" method="post">
            <label class="col-lg-6">Nom de la poule</label>
            <input type="text" class="col-lg-6"/>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Equipe</th>
                        <th>Selection</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                         <td><label for="team1">Equipe 1</label></td>
                        <td><input type="checkbox" id="team1"/></td>
                    </tr>
                    <tr>
                        <td><label for="team2">Equipe 2</label></td>
                        <td><input type="checkbox" id="team2"/></td>
                    </tr>
                </tbody>
            </table><button type="submit"  class="btn btn-primary">Créer</button>
        </form:form>
</div>

<c:forEach items="${pools}" var="p" varStatus="loop">

                            <td>${p.name}</td>

                    </c:forEach>
