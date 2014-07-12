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
                        <button type="button" class="btn btn-primary navbar-btn navbar-right tip" data-original-title="Se connecter" data-toggle="modal" role="button" href="#login_modal"><i class="icon-remove3"></i>Se connecter</button>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="login_modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">x</button>
                    <h4>Connexion</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputEmail" class="col-sm-3 control-label"><spring:message code="tournament.form.loginLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code='tournament.form.loginPlaceholder' var="loginPlaceholder"/>
                                <input type="email" class="form-control" id="inputEmail" placeholder="${loginPlaceholder}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-3 control-label"><spring:message code="tournament.form.passwordLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code='tournament.form.passwordPlaceholder' var="passwordPlaceholder"/>
                                <input type="password" class="form-control" id="inputPassword" placeholder="${passwordPlaceholder}">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Annuler</a>
                    <a href="#" class="btn btn-primary">Se connecter</a>
                </div>
            </div>
        </div>
    </div>
</header>