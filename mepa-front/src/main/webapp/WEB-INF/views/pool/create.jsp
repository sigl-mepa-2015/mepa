<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Créer une poule</h1>
	</div>
</div>

<div class="container">
        <form:form role="form"  modelAttribute="createPoolFormBean" method="post">
            <label class="col-lg-6">Nom de la poule</label>
            <input type="text" class="col-lg-6" name="name"><br/>
            <table class="table table-bordered">
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
                                 <td><input type="checkbox" name="teams" value="${t.id}""/></td>
                            <tr>

                     </c:forEach>
                </tbody>
            </table><button type="submit"  class="btn btn-primary">Créer</button>
        </form:form>
</div>


