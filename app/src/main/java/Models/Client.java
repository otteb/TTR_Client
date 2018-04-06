package Models;

import java.util.ArrayList;
import java.util.HashMap;

import Client_Server_Communication.Poller;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Game;
import Models.Gameplay.Player;
import ObserverPattern.TTR_Observable;
import StatePattern.State;

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
    private String userName;
    private String password;
    private String ipAddress;
    private String port;
    private String authToken;
    private Request loginRequest;
    private Request registerRequest;
    private int commandNum = 0;
    private State curState;

    //constructor
    private Client(){
        this.gameMap = new HashMap<>();
        isLoggedIn = false;
        activeGame = null;
    }

    //Helpful functions
    public void incCommandNum(int num) {
        commandNum += num;
    }

    public Game getGameById(String gameId) {
        return gameMap.get(gameId);
    }

    public ArrayList<Player> getGamePlayers(String gameId) {
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
        }
        TTR_Observable.getInstance().joinGame();
    }

    public void removePlayerFromGame(String gameId, String username) {
        getGamePlayers(gameId).remove(getGameById(gameId).getPlayer(username));
        TTR_Observable.getInstance().leaveGame();
    }

    public void addGameToMap(String gameId, Game game) {
        gameMap.put(gameId, game);
       TTR_Observable.getInstance().createGame();
    }

    public void removeGameFromMap(String gameId) {
        gameMap.remove(gameId);
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

    public State getCurState() {
        return curState;
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

    public void setAuthToken(String auth) {
        authToken = auth;
    }

    public void setPassword(String p)
    {
        password = p;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

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

    public void setCurState(State curState) {
        this.curState = curState;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void resetActiveGame() {
        activeGame = null;
        ActiveGame.getInstance().reset();
    }
}