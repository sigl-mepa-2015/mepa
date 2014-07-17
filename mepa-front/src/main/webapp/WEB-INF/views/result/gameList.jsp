<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<script>
function myFunction()
{
    document.getElementById("Start").disabled=true;
    document.getElementById("end").disabled=false
    document.getElementById("score1").value="0";
    document.getElementById("score1").disabled=false;
}
</script>

<div>
	<div class="page-header">
		<h1>Liste des match</h1>
	</div>
</div>

<div>
     <input type="HIDDEN" name="poolID" value="${poolID}">
            </br>
            <table class="table table-striped table-bordered" id="rangeTable">
                <thead>
                    <tr>
                        <th>Equipe 1</th>
                        <th>Score eq 1</th>
                        <th>Equipe 2</th>
                        <th>Score eq 2</th>
                        <th>Lancer match</th>
                        <th>Terminé match</th>
                        <th>Etat</th>
                        <th>Durée</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${gameList}" var="g" varStatus="loop">
                         <tr>
                             <form:form id="myform" role="form" action="${pageContext.request.contextPath}/updateGame" method="post">
                                 <input type="HIDDEN" name="gameID" value="${g.id}">
                                 <c:set var="i" value="1"/>
                                 <c:forEach items="${g.joinedGameTeams}" var="joined" varStatus="loop">
                                     <td>${joined.team.name}</td>
                                     <td><input type="text" id="score1" disabled="true" name="resultEquipe${i}" size="2" value="${joined.score}"/></td>
                                     <input type="HIDDEN" name="joinedID${i}" value="${joined.id}" />
                                     <c:set var="i" value="${i + 1}"/>
                                 </c:forEach>

                                     <td><button type="button" onclick="myFunction()" enabled="true" id="Start" class="btn btn-default btn-lg"> LM <Button/>
                                </td>
                                 <td><button type="button" disabled="true" id="end" class="btn btn-default btn-lg"> TM <Button/></td>
                                 <td><input type="text" name="status" size="4"  disabled="true" value="${g.status}"/></td>
                                 <td><input type="text" id="duration" size="2" disabled="true" name="duration" value="${g.duration}"/></td>
                                 <td><input type="submit" id="valider"/></td>

                             </form:form>
                         </tr>
                     </c:forEach>

                </tbody>
            </table>
    <a class="pull-left btn btn-default" href="/mepa-front/tournament/view/${poolID}" title="Annuler">
        <i class="glyphicon glyphicon-arrow-left"></i>
        Annuler
    </a>

</div>
</div>


