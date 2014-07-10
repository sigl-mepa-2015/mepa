<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 10/07/2014
  Time: 11:02
--%>
<form:form role="form" action="${removeTournamentActionUrl}" modelAttribute="removeTournamentFormBean" method="post">
    <form:input path="id" name="id" type="hidden" value="${tournament.id}"/>
    <button class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button>
</form:form>