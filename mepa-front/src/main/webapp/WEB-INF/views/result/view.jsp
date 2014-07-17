<%-- 
    Document   : view
    Created on : 16 juil. 2014, 23:41:46
    Author     : jason
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div>
    <div class="page-header">
       <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/result/afficherGame?poolID=${poolID}'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="result.bar.title.nav1"/>
        </button>
        <button type="button" class="pull-right btn btn-success" onClick="location.href = '${pageContext.request.contextPath}/result/teamScore?teamID=1'">
            <span class="glyphicon glyphicon-plus"></span> <spring:message code="result.bar.title.nav2"/>
        </button>
        <h1>Saisie des infos des match  </h1>
    </div>
</div>
 <div>
        <button type="button" class="btn btn-info" onClick="location.href='${pageContext.request.contextPath}/result/teamScore?teamID=1'">
           RIO
        </button>
    </div>
