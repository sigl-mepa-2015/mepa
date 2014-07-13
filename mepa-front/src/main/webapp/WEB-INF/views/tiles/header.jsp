<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<header>
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <!-- Mettre la variable dev à false lors du Recettage car le client
                     ne souhaite pas voir apparaître Tournoi et Gestion des équipes -->
                <c:set var="dev" value="true"/>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" name-toggle="collapse" name-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <c:set var="req" value="${pageContext.request}" />
                    <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
                    <a class="navbar-brand" href="${baseURL}">MEPA</a>
                </div>
                <div class="navbar-collapse collapse">
                <c:if test="${dev}">
                    <ul class="nav navbar-nav">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Tournoi <span
                                class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${pageContext.request.contextPath}/tournament/form">Créer un tournoi</a></li>
                                <li><a href="${pageContext.request.contextPath}/tournament/">Tous les tournois</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="home.bar.title2"/> <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${pageContext.request.contextPath}/team/form"><spring:message code="home.bar.title2.nav1"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/team/all"><spring:message code="home.bar.title2.nav2"/></a></li>
                            </ul>
                        </li>
                    </ul>
                </c:if>
                    <ul class=nav navbar-nav>
                        <sec:authorize access="isAnonymous()">
                        <button type="button" class="btn btn-primary navbar-btn navbar-right tip" data-original-title="Se connecter" data-toggle="modal" role="button" href="#login_modal"><i class="icon-remove3"></i>Se connecter</button>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                        <a href="mepa-front/j_spring_security_logout" type="button" class="btn btn-danger navbar-btn navbar-right tip" data-original-title="Se connecter"  role="button" href="/login"><i class="icon-remove3"></i>Se déconnecter</a>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="login_modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="form-horizontal" role="form" method="post" action="j_spring_security_check">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4>Connexion</h4>
                </div>
                <div class="modal-body">

                        <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message code="tournament.form.loginLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code='tournament.form.loginPlaceholder' var="loginPlaceholder"/>
                                <input class="form-control" name="j_username" id="inputId" placeholder="${loginPlaceholder}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-3 control-label"><spring:message code="tournament.form.passwordLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code='tournament.form.passwordPlaceholder' var="passwordPlaceholder"/>
                                <input type="password" class="form-control" name="j_password" id="inputPassword" placeholder="${passwordPlaceholder}">
                            </div>
                        </div>
                    </div>
                <div class="modal-footer">
                            <button type="submit" class="btn btn-default">Se connecter</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
                        </div>
                </form>
            </div>
        </div>
    </div>
</header>