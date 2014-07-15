<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Poule ${pool.name}</h1>
	</div>
</div>

<div class="container">
    Liste des équipes :
    <ul>
     <c:forEach items="${pool.teams}" var="t" varStatus="loop">

                                    <li>${t.name}</li>

                         </c:forEach>
</ul>
Liste des matchs :
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


