<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<header>
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <c:set var="req" value="${pageContext.request}" />
                    <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
                    <a class="navbar-brand" href="${baseURL}">MEPA</a>
                </div>
                <sec:authorize access="isAnonymous()">

                    <button type="button" class="btn btn-primary navbar-btn navbar-right tip" data-toggle="modal" role="button" href="#login_modal"> <span class="hidden-xs"><spring:message code="login" /></span>&nbsp;<i class="glyphicon glyphicon-log-in"></i></button>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout" type="button" class="btn btn-danger navbar-btn navbar-right tip" role="button"><span class="hidden-xs"><spring:message code="logout" /></span>&nbsp;<i class="glyphicon glyphicon-log-out"></i></a>
                </sec:authorize>
            </div>
        </div>
    </div>
    <div id="login_modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times</button>
                    <h4><spring:message code="log-in" /></h4>
                </div>
                <div class="modal-body">

                        <div class="form-group">
                            <label class="col-sm-3 control-label"><spring:message code="form.loginLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code="form.loginPlaceholder" var="loginPlaceholder" />
                                <input class="form-control" name="j_username" id="inputId" placeholder="${loginPlaceholder}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-3 control-label"><spring:message code="form.passwordLabel" /></label>
                            <div class="col-sm-8">
                                <spring:message code="form.passwordPlaceholder" var="passwordPlaceholder" />
                                <input type="password" class="form-control" name="j_password" id="inputPassword" placeholder="${passwordPlaceholder}" />
                            </div>
                        </div>
                    </div>
                <div class="modal-footer">
                            <button type="submit" class="btn btn-primary"><spring:message code="log-in" /></button>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                        </div>
                </form>
            </div>
        </div>
    </div>
</header>