package fr.epita.sigl.mepa.front.controller.util;

import java.util.HashSet;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import fr.epita.sigl.mepa.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;

@Controller
@RequestMapping("/injectData")
public class InjectDataController {

	@Autowired
	private TournamentService tournamentservice;
	
	@Autowired
	private PoolService poolservice;
	
	@Autowired
	private TeamService teamservice;
	
	@Autowired
	private GameService gameservice;

    @Autowired
    private PlayerService playerservice;

    @Autowired
    private MepaUserService mepauserservice;
	
	@Autowired
	private JoinedGameTeamService jgservice;
	
	@RequestMapping(value="/cleanDatabase", method=RequestMethod.GET)
	@ResponseBody
	public void cleanData()
	{
		for(Tournament t : tournamentservice.getAllTournaments())
		{
			tournamentservice.deleteTournament(t);
		}	
	}
	
	@RequestMapping(value="/min", method=RequestMethod.GET)
	@ResponseBody
	public void injectDataMin()
	{
		
	}

    private Pool createPool(int nb, Tournament t)
    {
        Pool p = new Pool();
        p.setName("PoolInjectViaController" + nb);
        p.setTournament(t);
        poolservice.createPool(p);
        return p;
    }

    private Team createTeam(int nb, Pool p, Tournament t)
    {
        Team team = new Team();
        team.setName("TeamInject" + nb);
        team.setPool(p);
        team.setTournament(t);
        teamservice.createTeam(team);
        return team;
    }

    private Game createGame(Pool p, Team t1, Team t2)
    {
        Game game = new Game();
        game.setStatus(Game.GameStatus.TODO);
        game.setPool(p);
        gameservice.createGame(game);

        JoinedGameTeam jg1 = new JoinedGameTeam();
        jg1.setGame(game);
        jg1.setTeam(t1);
        jgservice.createJoinedGameTeam(jg1);
        JoinedGameTeam jg2 = new JoinedGameTeam();
        jg2.setGame(game);
        jg2.setTeam(t2);
        jgservice.createJoinedGameTeam(jg2);

        return game;
    }

	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public void injectData()
	{
		Tournament t = new Tournament();
		t.setName("TournamentInjectViaController");
		tournamentservice.createTournament(t);

		Pool p1 = createPool(1, t);
		Pool p2 = createPool(2, t);
		Pool p3 = createPool(3, t);
		
		Team team1 = createTeam(1, p1, t);
		Team team2 = createTeam(2, p1, t);
		Team team3 = createTeam(3, p1, t);
		
		Team team4 = createTeam(4, p2, t);
		Team team5 = createTeam(5, p2, t);
		Team team6 = createTeam(6, p2, t);
		
		Team team7 = createTeam(7, p3, t);
		Team team8 = createTeam(8, p3, t);
		Team team9 = createTeam(9, p3, t);
		
		Game game1 = createGame(p1, team1, team2);
		Game game2 = createGame(p1, team1, team3);
		Game game3 = createGame(p1, team2, team3);
		Game game4 = createGame(p1, team2, team1);
		Game game5 = createGame(p1, team3, team1);
		Game game6 = createGame(p1, team3, team2);

        Game game7 = createGame(p2, team4, team5);
        Game game8 = createGame(p2, team4, team6);
        Game game9 = createGame(p2, team5, team6);
        Game game10 = createGame(p2, team5, team4);
        Game game11 = createGame(p2, team6, team4);
        Game game12 = createGame(p2, team6, team5);

        Game game13 = createGame(p3, team7, team8);
        Game game14 = createGame(p3, team7, team9);
        Game game15 = createGame(p3, team8, team9);
        Game game16 = createGame(p3, team8, team7);
        Game game17 = createGame(p3, team9, team7);
        Game game18 = createGame(p3, team9, team8);
	}
	
	private HashSet<Team> instanceHashSet(Team t1, Team t2)
	{
		HashSet<Team> h = new HashSet<Team>();
		h.add(t1);
		h.add(t2);
		
		return h;
	}
	
	@RequestMapping(value="/updateTournament", method=RequestMethod.GET)
	@ResponseBody
	public void updateData()
	{
		/*Random randomGenerator = new Random();
		
		Tournament t = tournamentservice.getTournamentById(Long.parseLong("1"));
		for (Pool p : t.getPools())
		{
			for (Game g : p.getGames())
			{
				if (randomGenerator.nextInt(1) == 1)
				{
					g.setResultTeam1(randomGenerator.nextInt(5));
					g.setResultTeam2(randomGenerator.nextInt(5));
					g.setDuration(randomGenerator.nextInt(100) + 20);
					g.setStatus(GameStatus.DONE);
					
					if (g.getResultTeam1() == g.getResultTeam2())
					{
						g.getTeams().
					}
				}
			}
		}*/
	}
	
	
	
	
	
}
