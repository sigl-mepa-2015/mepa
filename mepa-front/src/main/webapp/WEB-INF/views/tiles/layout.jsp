<%@ include file="/WEB-INF/views/includes/common.jsp"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <%-- Page title --%>
        <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
        <title><fmt:message key="${titleKey}"/></title>
        

        <%-- Bootstrap CSS --%>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css" />

        <%-- Application CSS --%>
        <c:url var="defaultCssUrl" value="/css/default.css" />
        <link rel="stylesheet" href="${defaultCssUrl}" type="text/css" />
       	
       	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/DataTables-1.10.0/media/css/jquery.dataTables.css" type="text/css" />
       	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css" type="text/css" />
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon.png" />
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/img/favicon.png" />

        <%-- jQuery --%>
        <script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
        <%-- Bootstrap JavaScript --%>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/Chart.min.js"></script>
        
        <script type="application/javascript" src="${pageContext.request.contextPath}/js/DataTables-1.10.0/media/js/jquery.dataTables.js"></script>
        <script type="application/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap.js"></script>
        
        
    </head>
    <body>
        <div class="wrapper">
            <%-- Header --%>
            <tiles:insertAttribute name="header" />
            <%-- Body content --%>
            <div class="container">
	           <div class="row">
		           	<div class="col-md-10">
		            	<tiles:insertAttribute name="body" />
		            </div>
		            <div class="col-md-2">
		            	<a class="twitter-timeline" href="https://twitter.com/MepaSigl" data-widget-id="489393956145225728">Tweets de @MepaSigl</a>
						<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

		            </div>
	            </div>
            </div/>
	            
	        <div class="push"></div>
            
        </div>
        <%-- Footer --%>
        <tiles:insertAttribute name="footer" />
    </body>
</html>