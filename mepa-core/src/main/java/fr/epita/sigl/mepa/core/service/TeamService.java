package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Team;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by david on 10/07/14.
 */

public interface TeamService {

    void createTeam(Team team);

    void updateTeam(Team team);

    void deleteTeam(Team team);

    Team getTeamById(Long id);

    List<Team> getAllTeams();

    List<Team> getAllOrderTeamsByPhase(Long tournamentID);

    HashMap<String, Long> getRangeByPhaseId(Long tournamentID,
                                            Team team);

    JSONArray constructJSONforResultChart(Team t) throws JSONException;

    JSONArray constructJSONForScoreChart(Long teamId) throws JSONException;
}
