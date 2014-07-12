package fr.epita.sigl.mepa.front.controller.util;

import java.util.HashSet;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;
import fr.epita.sigl.mepa.core.service.TournamentService;

@Controller
@RequestMapping("/injectData")
public class InjectDataController {

	@Autowired
	private TournamentService tournamentservice;
	
	@RequestMapping(value="/cleanDatabase", method=RequestMethod.GET)
	@ResponseBody
	public void cleanData()
	{
		for(Tournament t : tournamentservice.getAllTournaments())
		{
			tournamentservice.deleteTournament(t);
		}	
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public void injectData()
	{
		Tournament t = new Tournament();
		t.setName("TournamentInjectViaController");
		
		Pool p1 = new Pool();
		p1.setName("PoolInjectViaController1");
		p1.setTournament(t);
		Pool p2 = new Pool();
		p2.setName("PoolInjectViaController2");
		p2.setTournament(t);
		Pool p3 = new Pool();
		p3.setName("PoolInjectViaController3");
		p3.setTournament(t);
		
		Team team1 = new Team();
		team1.setName("TeamInject1");
		team1.setPool(p1);
		team1.setTournament(t);
		Team team2 = new Team();
		team2.setName("TeamInject2");
		team2.setPool(p1);
		team2.setTournament(t);
		Team team3 = new Team();
		team3.setName("TeamInject3");
		team3.setPool(p1);
		team3.setTournament(t);
		
		Team team4 = new Team();
		team4.setName("TeamInject4");
		team4.setPool(p2);
		team4.setTournament(t);
		Team team5 = new Team();
		team5.setName("TeamInject5");
		team5.setPool(p2);
		team5.setTournament(t);
		Team team6 = new Team();
		team6.setName("TeamInject6");
		team6.setPool(p2);
		team6.setTournament(t);
		
		Team team7 = new Team();
		team7.setName("TeamInject7");
		team7.setPool(p3);
		team7.setTournament(t);
		Team team8 = new Team();
		team8.setName("TeamInject8");
		team8.setPool(p3);
		team8.setTournament(t);
		Team team9 = new Team();
		team9.setName("TeamInject9");
		team9.setPool(p3);
		team9.setTournament(t);
		
		Game game1 = new Game();
		game1.setTeams(instanceHashSet(team1, team2));
		game1.setStatus(Game.GameStatus.TODO);
		game1.setPool(p1);
		
		Game game2 = new Game();
		game2.setTeams(instanceHashSet(team1, team3));
		game2.setStatus(Game.GameStatus.TODO);
		game2.setPool(p1);
		
		Game game3 = new Game();
		game3.setTeams(instanceHashSet(team2, team3));
		game3.setStatus(Game.GameStatus.TODO);
		game3.setPool(p1);
		
		Game game4 = new Game();
		game4.setTeams(instanceHashSet(team2, team1));
		game4.setStatus(Game.GameStatus.TODO);
		game4.setPool(p1);
		
		Game game5 = new Game();
		game5.setTeams(instanceHashSet(team3, team1));
		game5.setStatus(Game.GameStatus.TODO);
		game5.setPool(p1);
		
		Game game6 = new Game();
		game6.setTeams(instanceHashSet(team2, team3));
		game6.setStatus(Game.GameStatus.TODO);
		game6.setPool(p1);
		
		
		Game game7 = new Game();
		game7.setTeams(instanceHashSet(team4, team5));
		game7.setStatus(Game.GameStatus.TODO);
		game7.setPool(p2);
		
		Game game8 = new Game();
		game8.setTeams(instanceHashSet(team4, team6));
		game8.setStatus(Game.GameStatus.TODO);
		game8.setPool(p2);
		
		Game game9 = new Game();
		game9.setTeams(instanceHashSet(team5, team6));
		game9.setStatus(Game.GameStatus.TODO);
		game9.setPool(p2);
		
		Game game10 = new Game();
		game10.setTeams(instanceHashSet(team5, team4));
		game10.setStatus(Game.GameStatus.TODO);
		game10.setPool(p2);
		
		Game game11 = new Game();
		game11.setTeams(instanceHashSet(team6, team4));
		game11.setStatus(Game.GameStatus.TODO);
		game11.setPool(p2);
		
		Game game12 = new Game();
		game12.setTeams(instanceHashSet(team5, team6));
		game12.setStatus(Game.GameStatus.TODO);
		game12.setPool(p2);
		
		
		Game game13 = new Game();
		game13.setTeams(instanceHashSet(team7, team8));
		game13.setStatus(Game.GameStatus.TODO);
		game13.setPool(p3);
		
		Game game14 = new Game();
		game14.setTeams(instanceHashSet(team7, team9));
		game14.setStatus(Game.GameStatus.TODO);
		game14.setPool(p3);
		
		Game game15 = new Game();
		game15.setTeams(instanceHashSet(team8, team9));
		game15.setStatus(Game.GameStatus.TODO);
		game15.setPool(p3);
		
		Game game16 = new Game();
		game16.setTeams(instanceHashSet(team8, team7));
		game16.setStatus(Game.GameStatus.TODO);
		game16.setPool(p3);
		
		Game game17 = new Game();
		game17.setTeams(instanceHashSet(team9, team7));
		game17.setStatus(Game.GameStatus.TODO);
		game17.setPool(p3);
		
		Game game18 = new Game();
		game18.setTeams(instanceHashSet(team8, team9));
		game18.setStatus(Game.GameStatus.TODO);
		game18.setPool(p3);
		
		tournamentservice.createTournament(t);
		
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
