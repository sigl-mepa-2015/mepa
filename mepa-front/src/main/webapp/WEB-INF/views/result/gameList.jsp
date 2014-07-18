<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<script>

    $(function() {
        $('.Start').click(
                function() {
            $(this).attr("disabled",true);
            //document.getElementById("Start").disabled=true;
      
            //document.getElementById("end").disabled=false
             $(".score1",$(this).parent().parent()).val(0);
            //document.getElementById("score1").value="0";
              $(".score1",$(this).parent().parent()).attr("disabled", false);
              $("#duration",$(this).parent().parent()).attr("disabled", false);
              $(".status",$(this).parent().parent()).val('En cours');
               $("#submit",$(this).parent()).attr("disabled", true);
            //document.getElementById("score1").disabled=false;        
        });
         $('.end').click(
                function() {
            $(this).attr("disabled",true);
            $(".Start",$(this).parent().parent()).attr("disabled", true);
            $(".score1",$(this).parent().parent()).attr("disabled", false);
            $("#duration",$(this).parent().parent()).attr("disabled", false);
            $(".status",$(this).parent().parent()).val('à valider');
                  });
             });
</script>

<div>
	<div class="page-header">
		<h1>Liste des matchs</h1>
	</div>
</div>

<div>
     <input type="HIDDEN" name="poolID" value="${poolID}">
            </br>
            <table class="table table-striped table-bordered" id="rangeTable">
                <thead>
                    <tr>
                        <th>Equipe 1</th>
                        <th>Score eq 1</th>
                        <th>Equipe 2</th>
                        <th>Score eq 2</th>
                        <th>Saisie des scores en live</th>
                        <th>Saisie des scores finaux</th>
                        <th>Etat</th>
                        <th>DurÃ©e</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${gameList}" var="g" varStatus="loop">
                         <tr>
                             <form:form id="myform" role="form" action="${pageContext.request.contextPath}/result/updateGame" method="post">
                                 <input type="HIDDEN" name="gameID" value="${g.id}">
                                 <c:set var="i" value="1"/>
                                 <c:forEach items="${g.joinedGameTeams}" var="joined" varStatus="loop">
                                     <td>${joined.team.name}</td>
                                     <td><input type="text" class="score1" disabled="true" name="resultEquipe${i}" size="2" value="${joined.score}"/></td>
                                     <input type="HIDDEN" name="joinedID${i}" value="${joined.id}" />
                                     <c:set var="i" value="${i + 1}"/>
                                 </c:forEach>
                                     
                                     <c:if test="${g.status == 'DONE'}">
                                 <td><button type="button" disabled="true"  class="btn btn-default btn-lg Start"> Direct </button></td>   
                                <td><button  type="button" disabled="true"  class="btn btn-default btn-lg end"> Fin </button></td>
                                 <td><input type="text" class="status" disabled="true" name="status" size="6"   value="${g.status}"/></td>
                                 <td><input type="text" id="duration" size="2" disabled="true" name="duration" value="${g.duration}"></td> 
                                 <td><button disabled="true" type="submit" id="valider"/> Valider </button></td>
                                     </c:if>
                                
                                 <c:if test="${(g.status == 'TODO')}">
                                       <td><button type="button"  class="btn btn-default btn-lg Start"> Direct </button></td>   
                                <td><button  type="button"   class="btn btn-default btn-lg end"> Fin </button></td>
                                 <td><input type="text" class="status" name="status" disabled="true" size="6"   value="${g.status}"/></td>
                                 <td><input type="text" id="duration" size="2" disabled="true" name="duration" value="${g.duration}"/>
                                 <input type="HIDDEN" class="status" name="Status" value="${g.status}">
                                 <td><button type="submit" id="valider"/> Valider </button></td>
                                 </c:if>
                                
                                   <c:if test="${(g.status == 'PROGRESS')}">
                                         <td><button type="button" disabled="true" class="btn btn-default btn-lg Start"> Direct </button></td>   
                                         <td><button  type="button"   class="btn btn-default btn-lg end"> Fin </button></td>
                                         <td><input type="text" class="status" name="status" disabled="true" size="6"   value="${g.status}"/></td>
                                         <td><input type="text" id="duration" size="2"  name="duration" value="${g.duration}"/></td>
                                      <input type="HIDDEN" class="status" name="Status" value="${g.status}">
                                         <td><button type="submit" id="valider"/> Valider </button></td>
                                 </c:if>
                                
                                       

                             </form:form>
                         </tr>
                     </c:forEach>

                </tbody>
            </table>
    <a class="pull-left btn btn-default" href="${pageContext.request.contextPath}/poolManager?poolID=${pools}" title="Retour">
        <i class="glyphicon glyphicon-arrow-left"></i>
       Retour
    </a>

</div>
</div>


