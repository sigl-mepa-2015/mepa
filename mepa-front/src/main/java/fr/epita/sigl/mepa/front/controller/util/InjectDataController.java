package fr.epita.sigl.mepa.front.controller.util;

import java.io.Console;
import java.util.*;

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

    private long lastLoadTournamentId = 0;

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

    private MepaUser createUser(String name, String login, String pwd) {
        MepaUser user = new MepaUser();
        user.setName(name);
        user.setLogin(login);
        user.setPwd(pwd);
        mepauserservice.createMepaUser(user);
        return user;
    }

    private Player createPlayer(String name, Team team, MepaUser user) {
        Player player = new Player();
        player.setName(name);
        player.setTeam(team);
//        player.setMepaUser(user);
        playerservice.createPlayer(player);
        return player;
    }


    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public void injectData(HttpServletRequest request)
    {
        Tournament t = new Tournament();
        t.setName("TournamentInjectViaController" + (this.lastLoadTournamentId + 1));
        tournamentservice.createTournament(t);
        this.lastLoadTournamentId = t.getId();

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

        //MepaUser mepaUser = createUser("alex", "aloubelou", "test");
    }

    private int whowin (int score1, int score2)
    {
        if (score1 == score2)
            return 0;
        if (score1 > score2)
            return 1;
        if (score2 > score1)
            return 2;
        return 0;
    }

    @RequestMapping(value="/playGame", method=RequestMethod.GET)
    @ResponseBody
    public void playGame()
    {
        if (this.lastLoadTournamentId != 0)
        {
            Random randomGenerator = new Random();
            Tournament t = tournamentservice.getTournamentById(this.lastLoadTournamentId);
            for (Pool p : t.getPools())
            {
                for (Game g : p.getGames())
                {
                    if (randomGenerator.nextInt(2) == 1)
                    {
                        int score1 = randomGenerator.nextInt(6);
                        int score2 = randomGenerator.nextInt(6);
                        int i = 0;
                        for (JoinedGameTeam jgt : g.getJoinedGameTeams())
                        {
                            if (i == 0) {
                                jgt.setScore(score1);
                                switch (whowin(score1, score2))
                                {
                                    case 0 :
                                        jgt.getTeam().addDraw();
                                        break;
                                    case 1 :
                                        jgt.getTeam().addWin();
                                        break;
                                    case 2 :
                                        jgt.getTeam().addLose();
                                        break;
                                }
                            }
                            else
                            {
                                jgt.setScore(score2);
                                switch (whowin(score1, score2))
                                {
                                    case 0 :
                                        jgt.getTeam().addDraw();
                                        break;
                                    case 1 :
                                        jgt.getTeam().addLose();
                                        break;
                                    case 2 :
                                        jgt.getTeam().addWin();
                                        break;
                                }
                            }
                            i += 1;
                            teamservice.updateTeam(jgt.getTeam());
                            jgservice.updateJoinedGameTeam(jgt);
                        }
                        g.setDuration(randomGenerator.nextInt(101) + 20);
                        g.setStatus(GameStatus.DONE);
                        gameservice.updateGame(g);
                    }
                }
            }
            tournamentservice.getTournamentEndDate(t);
        }
    }
    
    @RequestMapping(value="/generateTournament", method=RequestMethod.GET)
    @ResponseBody
    public void generateTournament(@RequestParam("poolNumber") int poolNumber, @RequestParam("teamNumber") int teamNumber)
    {
    	Tournament t = new Tournament();
    	t.setName("GeneratedTournament" + poolNumber + "/" + teamNumber);
    	tournamentservice.createTournament(t);
    	
    	this.lastLoadTournamentId = t.getId();
    	
    	for (int i = 0; i < poolNumber; ++i)
    	{
    		Pool p = createPool(i, t);
    		List<Team> listTeam = new ArrayList<Team>();
    		for (int j = 0; j < teamNumber; j++)
    		{
    			Team te = createTeam(j, p, t);
    			listTeam.add(te);
    		}
    		for (Team t1 : listTeam)
    		{
    			for (Team t2 : listTeam)
    			{
    				if (t1 != t2)
    				{
    					createGame(p, t1, t2);
    				}	
    			}
    		}
    	}
    	
    }





}
