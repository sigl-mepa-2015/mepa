<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<header>
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" name-toggle="collapse" name-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">MEPA</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:url var="tournamentUrl" value="/tournament/"/>
                        <li><a href="${tournamentUrl}"><spring:message code="tournament.listTitle" /></a></li>
                        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="home.bar.title"/> <span class="caret"></span></a>
	                    	<ul class="dropdown-menu" role="menu">
	          				  <li><a href="${pageContext.request.contextPath}/reporting/endedGame"><spring:message code="home.bar.title.nav1"/></a></li>
	          				  <li><a href="${pageContext.request.contextPath}/reporting/comingGame"><spring:message code="home.bar.title.nav2"/></a></li>
	         				  <li><a href="${pageContext.request.contextPath}/reporting/showRange"><spring:message code="home.bar.title.nav3"/></a></li>
                            </ul>
				   </li>
                        <li><a href="${pageContext.request.contextPath}/creerPoule"><spring:message code="home.bar.title.nav1"/></a></li>

                    </ul>

                </div>
            </div>
        </div>
    </div>
</header>
