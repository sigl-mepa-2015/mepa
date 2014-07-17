<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
	<div class="page-header">
		<h1>Poule ${pool.name}</h1>
	</div>
</div>

<div>
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
    <%@ include file="/WEB-INF/views/pool/gameList.jsp"%>
</div>