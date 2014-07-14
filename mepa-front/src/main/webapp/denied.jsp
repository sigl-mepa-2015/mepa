<%--
  Created by IntelliJ IDEA.
  User: Nico
  Date: 13/07/14
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<h1>Accès Refusé</h1>
Vous n'avez pas les droits pour accéder à cette page.
En cas de soucis veuillez contacter un administrateur.<br/>
<a type="button" class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/tournament">Rediriger vers la page d'accueil</a>
</body>
</html>
