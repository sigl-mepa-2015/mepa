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
                    <a class="navbar-brand" href="#">MEPA</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:url var="homeUrl" value="/home"/>
                        <li><a href="${homeUrl}">Home page</a></li>
                        <c:url var="createTournamentUrl" value="/tournament/create/"/>
                        <li><a href="${createTournamentUrl}">Create Tournament</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>