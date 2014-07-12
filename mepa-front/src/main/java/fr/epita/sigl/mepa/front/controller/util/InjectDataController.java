package fr.epita.sigl.mepa.front.controller.util;

import java.util.HashSet;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;
import fr.epita.sigl.mepa.core.service.GameService;
import fr.epita.sigl.mepa.core.service.JoinedGameTeamService;
import fr.epita.sigl.mepa.core.service.PoolService;
import fr.epita.sigl.mepa.core.service.TeamService;
import fr.epita.sigl.mepa.core.service.TournamentService;

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
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public void injectData()
	{
		Tournament t = new Tournament();
		t.setName("TournamentInjectViaController");
		tournamentservice.createTournament(t);
		
		Pool p1 = new Pool();
		p1.setName("PoolInjectViaController1");
		p1.setTournament(t);
		poolservice.createPool(p1);
		Pool p2 = new Pool();
		p2.setName("PoolInjectViaController2");
		p2.setTournament(t);
		poolservice.createPool(p2);
		Pool p3 = new Pool();
		p3.setName("PoolInjectViaController3");
		p3.setTournament(t);
		poolservice.createPool(p3);
		
		Team team1 = new Team();
		team1.setName("TeamInject1");
		team1.setPool(p1);
		team1.setTournament(t);
		teamservice.createTeam(team1);
		Team team2 = new Team();
		team2.setName("TeamInject2");
		team2.setPool(p1);
		team2.setTournament(t);
		teamservice.createTeam(team2);
		Team team3 = new Team();
		team3.setName("TeamInject3");
		team3.setPool(p1);
		team3.setTournament(t);
		teamservice.createTeam(team3);
		
		Team team4 = new Team();
		team4.setName("TeamInject4");
		team4.setPool(p2);
		team4.setTournament(t);
		teamservice.createTeam(team4);
		Team team5 = new Team();
		team5.setName("TeamInject5");
		team5.setPool(p2);
		team5.setTournament(t);
		teamservice.createTeam(team5);
		Team team6 = new Team();
		team6.setName("TeamInject6");
		team6.setPool(p2);
		team6.setTournament(t);
		teamservice.createTeam(team6);
		
		Team team7 = new Team();
		team7.setName("TeamInject7");
		team7.setPool(p3);
		team7.setTournament(t);
		teamservice.createTeam(team7);
		Team team8 = new Team();
		team8.setName("TeamInject8");
		team8.setPool(p3);
		team8.setTournament(t);
		teamservice.createTeam(team8);
		Team team9 = new Team();
		team9.setName("TeamInject9");
		team9.setPool(p3);
		team9.setTournament(t);
		teamservice.createTeam(team9);
		
		Game game1 = new Game();
		game1.setStatus(Game.GameStatus.TODO);
		game1.setPool(p1);
		gameservice.createGame(game1);
		
		JoinedGameTeam jg1 = new JoinedGameTeam();
		jg1.setGame(game1);
		jg1.setTeam(team1);
		jgservice.createJoinedGameTeam(jg1);
		JoinedGameTeam jg2 = new JoinedGameTeam();
		jg2.setGame(game1);
		jg2.setTeam(team2);
		jgservice.createJoinedGameTeam(jg2);
		
		Game game2 = new Game();
		game2.setStatus(Game.GameStatus.TODO);
		game2.setPool(p1);
		gameservice.createGame(game2);
		
		JoinedGameTeam jg3 = new JoinedGameTeam();
		jg3.setGame(game2);
		jg3.setTeam(team1);
		jgservice.createJoinedGameTeam(jg3);
		JoinedGameTeam jg4 = new JoinedGameTeam();
		jg4.setGame(game2);
		jg4.setTeam(team3);
		jgservice.createJoinedGameTeam(jg4);
		
		Game game3 = new Game();
		game3.setStatus(Game.GameStatus.TODO);
		game3.setPool(p1);
		gameservice.createGame(game3);
		
		JoinedGameTeam jg5 = new JoinedGameTeam();
		jg5.setGame(game3);
		jg5.setTeam(team2);
		jgservice.createJoinedGameTeam(jg5);
		JoinedGameTeam jg6 = new JoinedGameTeam();
		jg6.setGame(game3);
		jg6.setTeam(team3);
		jgservice.createJoinedGameTeam(jg6);
		
		Game game4 = new Game();
		game4.setStatus(Game.GameStatus.TODO);
		game4.setPool(p1);
		gameservice.createGame(game4);
		
		JoinedGameTeam jg7 = new JoinedGameTeam();
		jg7.setGame(game4);
		jg7.setTeam(team2);
		jgservice.createJoinedGameTeam(jg7);
		JoinedGameTeam jg8 = new JoinedGameTeam();
		jg8.setGame(game4);
		jg8.setTeam(team1);
		jgservice.createJoinedGameTeam(jg8);
		
		Game game5 = new Game();
		game5.setStatus(Game.GameStatus.TODO);
		game5.setPool(p1);
		gameservice.createGame(game5);
		
		JoinedGameTeam jg9 = new JoinedGameTeam();
		jg9.setGame(game5);
		jg9.setTeam(team3);
		jgservice.createJoinedGameTeam(jg9);
		JoinedGameTeam jg10 = new JoinedGameTeam();
		jg10.setGame(game5);
		jg10.setTeam(team1);
		jgservice.createJoinedGameTeam(jg10);
		
		Game game6 = new Game();
		game6.setStatus(Game.GameStatus.TODO);
		game6.setPool(p1);
		gameservice.createGame(game6);
		
		JoinedGameTeam jg11 = new JoinedGameTeam();
		jg11.setGame(game6);
		jg11.setTeam(team2);
		jgservice.createJoinedGameTeam(jg11);
		JoinedGameTeam jg12 = new JoinedGameTeam();
		jg12.setGame(game6);
		jg12.setTeam(team3);
		jgservice.createJoinedGameTeam(jg12);
		
		Game game7 = new Game();
		game7.setStatus(Game.GameStatus.TODO);
		game7.setPool(p2);
		gameservice.createGame(game7);
		
		JoinedGameTeam jg13 = new JoinedGameTeam();
		jg13.setGame(game7);
		jg13.setTeam(team4);
		jgservice.createJoinedGameTeam(jg13);
		JoinedGameTeam jg14 = new JoinedGameTeam();
		jg14.setGame(game7);
		jg14.setTeam(team5);
		jgservice.createJoinedGameTeam(jg14);
		
		Game game8 = new Game();
		game8.setStatus(Game.GameStatus.TODO);
		game8.setPool(p2);
		gameservice.createGame(game8);
		
		JoinedGameTeam jg15 = new JoinedGameTeam();
		jg15.setGame(game8);
		jg15.setTeam(team4);
		jgservice.createJoinedGameTeam(jg15);
		JoinedGameTeam jg16 = new JoinedGameTeam();
		jg16.setGame(game8);
		jg16.setTeam(team6);
		jgservice.createJoinedGameTeam(jg16);
		
		Game game9 = new Game();
		game9.setStatus(Game.GameStatus.TODO);
		game9.setPool(p2);
		gameservice.createGame(game9);
		
		JoinedGameTeam jg17 = new JoinedGameTeam();
		jg17.setGame(game9);
		jg17.setTeam(team5);
		jgservice.createJoinedGameTeam(jg17);
		JoinedGameTeam jg18 = new JoinedGameTeam();
		jg18.setGame(game9);
		jg18.setTeam(team6);
		jgservice.createJoinedGameTeam(jg18);
		
		Game game10 = new Game();
		game10.setStatus(Game.GameStatus.TODO);
		game10.setPool(p2);
		gameservice.createGame(game10);
		
		JoinedGameTeam jg19 = new JoinedGameTeam();
		jg19.setGame(game10);
		jg19.setTeam(team5);
		jgservice.createJoinedGameTeam(jg19);
		JoinedGameTeam jg20 = new JoinedGameTeam();
		jg20.setGame(game10);
		jg20.setTeam(team4);
		jgservice.createJoinedGameTeam(jg20);
		
		Game game11 = new Game();
		game11.setStatus(Game.GameStatus.TODO);
		game11.setPool(p2);
		gameservice.createGame(game11);
		
		JoinedGameTeam jg21 = new JoinedGameTeam();
		jg21.setGame(game11);
		jg21.setTeam(team6);
		jgservice.createJoinedGameTeam(jg21);
		JoinedGameTeam jg22 = new JoinedGameTeam();
		jg22.setGame(game11);
		jg22.setTeam(team4);
		jgservice.createJoinedGameTeam(jg22);
		
		Game game12 = new Game();
		game12.setStatus(Game.GameStatus.TODO);
		game12.setPool(p2);
		gameservice.createGame(game12);
		
		JoinedGameTeam jg23 = new JoinedGameTeam();
		jg23.setGame(game12);
		jg23.setTeam(team5);
		jgservice.createJoinedGameTeam(jg23);
		JoinedGameTeam jg24 = new JoinedGameTeam();
		jg24.setGame(game12);
		jg24.setTeam(team6);
		jgservice.createJoinedGameTeam(jg24);
		
		Game game13 = new Game();
		game13.setStatus(Game.GameStatus.TODO);
		game13.setPool(p3);
		gameservice.createGame(game13);
		
		JoinedGameTeam jg25 = new JoinedGameTeam();
		jg25.setGame(game13);
		jg25.setTeam(team7);
		jgservice.createJoinedGameTeam(jg25);
		JoinedGameTeam jg26 = new JoinedGameTeam();
		jg26.setGame(game13);
		jg26.setTeam(team8);
		jgservice.createJoinedGameTeam(jg26);
		
		Game game14 = new Game();
		game14.setStatus(Game.GameStatus.TODO);
		game14.setPool(p3);
		gameservice.createGame(game14);
		
		JoinedGameTeam jg27 = new JoinedGameTeam();
		jg27.setGame(game14);
		jg27.setTeam(team7);
		jgservice.createJoinedGameTeam(jg27);
		JoinedGameTeam jg28 = new JoinedGameTeam();
		jg28.setGame(game14);
		jg28.setTeam(team9);
		jgservice.createJoinedGameTeam(jg28);
		
		Game game15 = new Game();
		game15.setStatus(Game.GameStatus.TODO);
		game15.setPool(p3);
		gameservice.createGame(game15);
		
		JoinedGameTeam jg29 = new JoinedGameTeam();
		jg29.setGame(game15);
		jg29.setTeam(team8);
		jgservice.createJoinedGameTeam(jg29);
		JoinedGameTeam jg30 = new JoinedGameTeam();
		jg30.setGame(game15);
		jg30.setTeam(team9);
		jgservice.createJoinedGameTeam(jg30);
		
		Game game16 = new Game();
		game16.setStatus(Game.GameStatus.TODO);
		game16.setPool(p3);
		gameservice.createGame(game16);
		
		JoinedGameTeam jg31 = new JoinedGameTeam();
		jg31.setGame(game16);
		jg31.setTeam(team8);
		jgservice.createJoinedGameTeam(jg31);
		JoinedGameTeam jg32 = new JoinedGameTeam();
		jg32.setGame(game16);
		jg32.setTeam(team7);
		jgservice.createJoinedGameTeam(jg32);
		
		Game game17 = new Game();
		game17.setStatus(Game.GameStatus.TODO);
		game17.setPool(p3);
		gameservice.createGame(game17);
		
		JoinedGameTeam jg33 = new JoinedGameTeam();
		jg33.setGame(game17);
		jg33.setTeam(team9);
		jgservice.createJoinedGameTeam(jg33);
		JoinedGameTeam jg34 = new JoinedGameTeam();
		jg34.setGame(game17);
		jg34.setTeam(team7);
		jgservice.createJoinedGameTeam(jg34);
		
		Game game18 = new Game();
		game18.setStatus(Game.GameStatus.TODO);
		game18.setPool(p3);
		gameservice.createGame(game18);
		
		JoinedGameTeam jg35 = new JoinedGameTeam();
		jg35.setGame(game18);
		jg35.setTeam(team8);
		jgservice.createJoinedGameTeam(jg35);
		JoinedGameTeam jg36 = new JoinedGameTeam();
		jg36.setGame(game18);
		jg36.setTeam(team9);
		jgservice.createJoinedGameTeam(jg36);
		
		System.out.println(t.getId());
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
