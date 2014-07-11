<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<%@ include file="/WEB-INF/views/tournament/namespace.jsp"%>
<%@ include file="/WEB-INF/views/tournament/create/form.jsp"%>

<div class="container">
    <c:choose>
        <c:when test="${not empty tournaments}">
        <h2><spring:message code="tournament.listTitle"/></h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><spring:message code="name"/></th>
                        <th><spring:message code="delete"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tournaments}" var="tournament" varStatus="loop">
                        <tr>
                            <td>${tournament.name}</td>
                            <td>
                                <%@ include file="/WEB-INF/views/tournament/remove/form.jsp"%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
            <h2><spring:message code="tournament.emptyTitle"/></h2>
    </c:otherwise>
    </c:choose>
</div>
