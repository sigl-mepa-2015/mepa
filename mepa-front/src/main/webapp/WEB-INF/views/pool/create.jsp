<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="jumbotron">
        <h1>Nouvelle Poule</h1>
        <p class="alert alert-success">Votre poule a été correctement crée</p>
        <div class="form-group">
            <form role="form">
                <label for="poolName">Nom de la poule </label>
                <input type="text" placeholder="Nom" id="poolName" class="form-control"><br/>

                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th>Equipe</th>
                            <th>Selection</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><label for="team1">Equipe 1</label></td>
                            <td><input type="checkbox" id="team1"/></td>
                        </tr>
                        <tr>
                            <td><label for="team2">Equipe 2</label></td>
                            <td><input type="checkbox" id="team2"/></td>
                        </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                    </tr>
                    </tfoot>
                </table>
                <button type="submit" class="btn btn-default">Créer</button>
            </form>
        </div>
        <!---p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget mauris a lacus commodo placerat.
            Pellentesque commodo, nisl mollis cursus ultrices, dui tellus molestie purus, ac lobortis lectus elit ac mi.
            Pellentesque tristique nisi nec massa auctor in interdum nisl mattis. Aenean convallis dignissim eleifend. Morbi
            quis tortor odio. Quisque at lectus sed arcu consectetur venenatis ac nec sem. Nulla vehicula eleifend iaculis.
            Donec libero purus, aliquet et volutpat porta, bibendum vel enim. Proin tempus rutrum iaculis. Proin vulputate
            dignissim lobortis. Aenean ante elit, condimentum sed fringilla nec, sagittis in magna.</p--->

        <p class="text-center">
            <c:url var="coreExampleUrl" value="/example/core/"/>
            <a class="btn btn-lg btn-primary" href="${coreExampleUrl}" role="button">View Core (Database) module and form validation example</a>
        </p>

        <!--p class="text-center well-done">
            <c:url var="wellDoneImgUrl" value="/img/welldone.jpg"/>
            <img src="${wellDoneImgUrl}" alt=""/>
        </p-->
    </div>
</div>