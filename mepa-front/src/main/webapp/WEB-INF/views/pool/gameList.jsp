<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Liste des match</h1>
	</div>
</div>

<div class="container">
            </br>
            <table class="table table-striped table-bordered" id="rangeTable">
                <thead>
                    <tr>
                        <th>Equipe 1</th>
                        <th>Score eq 1</th>
                        <th>Equipe 2</th>
                        <th>Score eq 2</th>
                        <th>Durée</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${gameList}" var="g" varStatus="loop">
                         <tr>
                             <form:form role="form" action="${pageContext.request.contextPath}/updateGame" method="post">
                                 <input type="HIDDEN" name="gameID" value="${g.id}">
                                 <c:set var="i" value="1"/>
                                 <c:forEach items="${g.getJoinedGameTeams()}" var="joined" varStatus="loop">
                                     <td>${(joined.getTeam().getName())}</td>
                                     <td><input type="text" name="resultEquipe${i}" size="2" value="${joined.getScore()}"/></td>
                                     <input type="HIDDEN" name="joinedID${i}" value="${joined.id}" />
                                     <c:set var="i" value="${i + 1}"/>
                                 </c:forEach>


                                 <td><input type="text" id="duration" name="duration" value="${g.getDuration()}"/></td>
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


