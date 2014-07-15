package fr.epita.sigl.mepa.front.controller.util;

import java.io.Console;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import fr.epita.sigl.mepa.core.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;
import fr.epita.sigl.mepa.front.controller.tournament.TournamentController;

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

    @Autowired
    private RoleService roleservice;

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

    private Team createTeam(int nb, Pool p, Tournament t, int nbPlayer)
    {
        Team team = new Team();
        team.setName("TeamInject" + nb);
        team.setPool(p);
        team.setTournament(t);
        teamservice.createTeam(team);

        for (int i = 0; i < nbPlayer; i++)
        {
            addGamer("Player-" + nb + "" + i, "Login-" + t.getId(), "test", team);
        }
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
        player.setMepaUser(user);
        player.setNbPoint(0);
        playerservice.createPlayer(player);
        return player;
    }

    private void linkRoleToUser(Role role, MepaUser user) {
        user.addRole(role);
        mepauserservice.updateMepaUser(user);
    }

    private void addGamer(String name, String login, String password, Team t)
    {
        Role role = roleservice.getRoleByAuthority("PLAYER");
        MepaUser user = createUser(name, login, password);
        Player player = createPlayer(name, t, user);
        linkRoleToUser(role, user);
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

    private void addPoint (Team t, int nbpoint)
    {
        Random randomGenerator = new Random();
        ArrayList<Player> players = new ArrayList<>(t.getPlayers());

        for (int i = 0; i < nbpoint; i++)
        {
            int rand = randomGenerator.nextInt(players.size());
            Player p = players.get(rand);
            p.setNbPoint(p.getNbPoint() + 1);
            System.out.println("Player " + p.getName() + " give 1 point to Team " + t.getName());
        }
    }

    @RequestMapping(value="/playGame", method=RequestMethod.GET)
    @ResponseBody
    public void playGame(long tournamentID, int pourcPlayed, int pourcPlaying, int maxPoint, int dmin, int dmax)
    {
        Random randomGenerator = new Random();
        Tournament t = tournamentservice.getTournamentById(tournamentID);
        if (t != null) {
            for (Pool p : t.getPools()) {
                for (Game g : p.getGames()) {
                    int rand = randomGenerator.nextInt(101);
                    if (rand <= pourcPlayed) {
                        int score1 = randomGenerator.nextInt(maxPoint + 1);
                        int score2 = randomGenerator.nextInt(maxPoint + 1);
                        int i = 0;
                        for (JoinedGameTeam jgt : g.getJoinedGameTeams()) {
                            if (i == 0) {
                                jgt.setScore(score1);
                                switch (whowin(score1, score2)) {
                                    case 0:
                                        jgt.getTeam().addDraw();
                                        break;
                                    case 1:
                                        jgt.getTeam().addWin();
                                        break;
                                    case 2:
                                        jgt.getTeam().addLose();
                                        break;
                                }
                                addPoint(jgt.getTeam(), score1);
                            } else {
                                jgt.setScore(score2);
                                switch (whowin(score1, score2)) {
                                    case 0:
                                        jgt.getTeam().addDraw();
                                        break;
                                    case 1:
                                        jgt.getTeam().addLose();
                                        break;
                                    case 2:
                                        jgt.getTeam().addWin();
                                        break;
                                }
                                addPoint(jgt.getTeam(), score2);
                            }
                            i += 1;
                            teamservice.updateTeam(jgt.getTeam());
                            jgservice.updateJoinedGameTeam(jgt);
                        }
                        g.setDuration(randomGenerator.nextInt(dmax + 1) + dmin);
                        g.setStatus(GameStatus.DONE);
                    } else {
                        if (rand <= (pourcPlayed + pourcPlaying)) {
                            g.setStatus(GameStatus.PROGRESS);
                        }
                    }
                    gameservice.updateGame(g);
                }
            }
        }
    }

    private void generateTournament(String tournamentName, int poolNumber, int teamNumber, int playerNumber, boolean return_game)
    {
    	Tournament t = new Tournament();
    	t.setName(tournamentName);
    	tournamentservice.createTournament(t);
    	
    	for (int i = 0; i < poolNumber; ++i)
    	{
    		Pool p = createPool(i, t);
    		List<Team> listTeam = new ArrayList<Team>();
    		for (int j = 0; j < teamNumber; j++)
    		{
                String nbTeam = "" + i;
                nbTeam += j;
    			Team te = createTeam(new Integer(nbTeam), p, t, playerNumber);
    			listTeam.add(te);
    		}
            if (return_game == true) {
                for (Team t1 : listTeam) {
                    for (Team t2 : listTeam) {
                        if (t1 != t2) {
                            createGame(p, t1, t2);
                        }
                    }
                }
            }
            else
            {
                for (int index = 0; index < listTeam.size(); index++)
                {
                    Team t1 = listTeam.get(index);
                    for (int j = index + 1; j < listTeam.size(); j++)
                    {
                        Team t2 = listTeam.get(j);
                        createGame(p, t1, t2);
                    }
                }
            }
    	}
    }
    
    @RequestMapping(value="/tournamentGenerator", method=RequestMethod.GET)
    public ModelAndView generateTournament()
    {
    	ModelAndView mv = new ModelAndView("/injectData/tournamentGenerator");
    	mv.addObject("allTournament", tournamentservice.getAllTournaments());
		
    	return mv;
    }
    
    @RequestMapping(value="/createTournament", method=RequestMethod.POST)
    public  String createTournament(@RequestParam("tournamentName") String tournamentName,
    		@RequestParam("poolNumber") int poolNumber,
    		@RequestParam("teamNumber") int teamNumber,
    		@RequestParam(value = "return", required = false) Boolean returnGame,
    		@RequestParam("playerNumber") int playerNumber)
    {
        if (returnGame == null)
    	    this.generateTournament(tournamentName, poolNumber, teamNumber, playerNumber, false);
        else
            this.generateTournament(tournamentName, poolNumber, teamNumber, playerNumber, returnGame);
        return "redirect:/tournament";
    }
    
    @RequestMapping(value="/playGame", method=RequestMethod.POST)
    public String playTournament(@RequestParam("tournamentId") int tournamentId,
    		@RequestParam("pourPlaying") int pourPlaying,
    		@RequestParam("currentPlaying") int currentPlaying,
    		@RequestParam("minGame") int minGame,
    		@RequestParam("maxGame") int maxGame,
    		@RequestParam("scoreMax") int scoreMax)
    {
    	this.playGame(tournamentId, pourPlaying, currentPlaying, scoreMax, minGame, maxGame);
        return "redirect:/tournament";
    }
    
}
