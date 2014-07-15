<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Poule ${pool.name}</h1>
	</div>
</div>

<div class="container">
   <h3> Classement des équipes : </h3><br/>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th width="20%">Classement</th>
                    <th >Equipe</th>
                </tr>
            </thead>
            <tbody>
            <c:set var="i" value="1"/>
                <c:forEach items="${pool.teams}" var="t"  varStatus="loop">
                    <tr>
                        <td width="20%">${i}</td>
                        <td >${t.name}</td>
                    </tr>
                <c:set var="i" value="${i+1}"/>
               </c:forEach>
            </tbody>
        </table>
    <br/>
   <h3>Liste des matchs :</h3>
   <ul>
    <c:forEach items="${pool.games}" var="g" varStatus="loop">
       <li>
           Identifiant du match : ${g.id}<br/>
           <c:forEach items="${g.joinedGameTeams}" var="jt" varStatus="loop">
                   ${jt.team.name}
            </c:forEach>
        </li>
    </c:forEach>
   </ul>
</div>