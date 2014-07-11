<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
	<div class="page-header">
		<h1>Créer une poule</h1>
	</div>
</div>

<div class="container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Equipe</th>
                    <th>Selection</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <td><label >Enter a team below:</label></td>
                <td><input  type="text" id="name"/></td>
            </tr>
                <tr>
                     <td><label for="team1">Equipe 1</label></td>
                    <td><input type="checkbox" id="team1"/></td>
                </tr>
                <tr>
                    <td><label for="team2">Equipe 2</label></td>
                    <td><input type="checkbox" id="team2"/></td>
                </tr>
            </tbody>
        </table>

        <button type="submit"  class="btn btn-primary">Créer</button>

</div>
