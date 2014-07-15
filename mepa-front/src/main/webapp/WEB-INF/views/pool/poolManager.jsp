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
    <c:choose>
                <c:when test="${not empty pool.games}">
                    Liste des matchs : ...
                </c:when>
                <c:otherwise>
                     <button class="btn btn-default">Générer les matchs</button>

                </c:otherwise>
            </c:choose>

</div>


