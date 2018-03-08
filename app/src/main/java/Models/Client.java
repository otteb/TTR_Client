package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Client_Server_Communication.Poller;
import Models.Gameplay.Game;
import Models.Gameplay.Player;
import ObserverPattern.TTR_Observable;

/**
 * Created by brianotte on 2/12/18.
 */

public class Client {// extends Observable {

    private static Client single_instance = new Client();
    public static Client getInstance()
    {
        return single_instance;
    }

    private Poller poller = new Poller();
    private HashMap<String, Game> gameMap;
    private Game activeGame;
    private boolean isLoggedIn;
//    private boolean isRegistered;
    private String userName;
    private String password;
    private String authToken;
    private Request loginRequest;
    private Request registerRequest;
    private int commandNum = 0;
    //properties specific to each Client in the active game:
//    private int activeGameCMDNum = 0;
//    private TTR_Observable obs = new TTR_Observable();

    //constructor
    private Client(){
//        this.commandNum = 0;
        this.gameMap = new HashMap<>();
        isLoggedIn = false;
        activeGame = null;
    }

    //Helpful functions
    public void incCommandNum(int num) {
        commandNum += num;
    }

//    public void incActiveGameCMDNum(int num){
//        this.activeGameCMDNum += num;
//    }

    public Game getGameById(String gameId) {
        return gameMap.get(gameId);
    }

    public ArrayList<Player> getGamePlayers(String gameId) {
//        Game test = getGameById(gameId);
//        test.getId();
        return getGameById(gameId).getPlayers();
    }

    public int getGameSize(String gameId) {
        return getGamePlayers(gameId).size();
    }

    public ArrayList<Game> getGameList(){
        ArrayList<Game> gameList = new ArrayList<>();
        for(Game g : gameMap.values())
        {
            gameList.add(g);
        }
        return gameList;
    }

    //observables section:
    public void addPlayerToGame(String gameId, String username) {
        for (String i : getGameMap().keySet())
        {
            for (int j=0; j< getGameSize(gameId); j++)
            {
                if(gameMap.get(i).getId().equals(gameId) && gameMap.get(i).getPlayers().get(j).getName().equals(username))
                {
                    gameMap.get(i).getPlayers().remove(j);
                }
            }
        }
        gameMap.get(gameId).addPlayer(new Player(username));
        if(this.userName.equals(username))
        {
            activeGame = gameMap.get(gameId);
            if(isLoggedIn)
            {
                //only call notify the observer of joinGame if the user is the one joining a game
                TTR_Observable.getInstance().joinGame();
            }
        }
    }

    public void removePlayerFromGame(String gameId, String username) {
        getGamePlayers(gameId).remove(getGameById(gameId).getPlayer(username));
        TTR_Observable.getInstance().leaveGame();
    }

    public void addGameToMap(String gameId, Game game) {
        gameMap.put(gameId, game);
       TTR_Observable.getInstance().createGame();
    }


    //GETTERS
    public Game getActiveGame()
    {
        return activeGame;
    }

    public HashMap<String, Game> getGameMap() {
        return gameMap;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public String getPassword()
    {
        return password;
    }

    public int getCommandNum() {
        return commandNum;
    }

//    public int getActiveGameCMDNum() {
//        return activeGameCMDNum;
//    }

    public boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }

    public Request getRegisterRequest() {
        return registerRequest;
    }

    public Request getLoginRequest() {
        return loginRequest;
    }


    // SETTERS
    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }

    public void setGameMap(HashMap<String, Game> gameMap) {
        this.gameMap = gameMap;
    }

    public void setUserName(String u)
    {
        userName = u;
    }

    public void setAuthToken(String a) {
        authToken=a;
        TTR_Observable.getInstance().setAuthToken(a);
    }

    public void setPassword(String p)
    {
        password=p;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

//    public void setActiveGameCMDNum(int activeGameCMDNum) {
//        this.activeGameCMDNum = activeGameCMDNum;
//    }

    public void setIsLoggedIn(boolean b)
    {
        isLoggedIn= b;
    }

    public void setLoginRequest(Request l)
    {
        loginRequest=l;
    }

    public void setRegisterRequest(Request r){ registerRequest=r; }


    //This is for users that are not get officially login to the system
    public void clientLogin(Request lR)
    {
        loginRequest = lR;
    }

    public Poller getPoller() {
        return poller;
    }

//    public Poller getPoller() {
//        return poller;
//    }
//
//    public void setPoller(Poller poller) {
//        this.poller = poller;
//    }
}