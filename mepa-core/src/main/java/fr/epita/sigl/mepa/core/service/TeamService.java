package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Team;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

/**
 * Created by david on 10/07/14.
 */

public interface TeamService {

    void createTeam(Team team);

    void updateTeam(Team team);

    void deleteTeam(Team team);

    Team getTeamById(Long id);

    List<Team> getAllTeams();

	List<Team> getAllOrderTeamsByTournament(Long tournamentID);

	HashMap<String, Long> getRangeByTounrnamentId(Long tournamentID,
			Team team);

	JSONArray constructJSONforResultChart(Team t) throws JSONException;
}
