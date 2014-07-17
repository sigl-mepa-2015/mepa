<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
    <div class="page-header">
        <h1>Créer une poule</h1>
    </div>
</div>

<div>
    <div id="message_box" class="alert alert-warning" style="display: none">
        Sélectionnez au minimum deux équipes.
    </div>
</div>

<div>
    <form:form role="form"  id="formulaire1" modelAttribute="createPoolFormBean" action="${pageContext.request.contextPath}/poolManager?tournamentID=${tournamentID}" method="POST">
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
        <button type="submit" class="btn btn-primary pull-right btn-success">Créer</button>
    </form:form>
    <a class="pull-left btn btn-default" href="${pageContext.request.contextPath}/tournament/view/${tournamentID}" title="Annuler">
        Annuler
    </a>

</div>

<script type="text/javascript">

    $(function() {
        $('#formulaire1').submit(function() {
            if ($('input[type="checkbox"]:checked', $(this)).size() >= 2)
                return true;
            else
                $("#message_box").show(0).delay(1500).slideUp();
            return false;
        });
    });
</script>
