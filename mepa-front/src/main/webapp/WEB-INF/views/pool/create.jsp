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
                    <tr>
                    <td>Equipe 1</td>
                   <td><input type="checkbox" name="teams" value="Equipe 1"/></td>

                    </tr>
                    <tr>
                       <td>Equipe 2</td>
                       <td><input type="checkbox" name="teams" value="Equipe 2"/></td>
                    </tr>
                </tbody>
            </table><button type="submit"  class="btn btn-primary">Créer</button>
        </form:form>
</div>

<div class="container">

<table class="table table-bordered">
    <thead>
    <tr>
        <th>
            Nom
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        
    <c:if test="${message == true}">
        <div id="message_box" class="alert alert-success">
            <spring:message code="pool.create.resultMessageSucces" arguments="${pool.name}" />
        </div>
    </c:if>
    <c:if test="${message == false}">
        <div id="message_box" class="alert alert-fail">
            <spring:message code="pool.create.resultMessageFail" arguments="${pool.name}" />
        </div>
    </c:if>

    <c:forEach items="${pools}" var="p" varStatus="loop">
        <tr>        <td>${p.name}</td><tr>

    </c:forEach>
    </tbody>
</table>
</div>


