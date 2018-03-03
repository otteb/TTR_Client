package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Models.Gameplay.Game;
import Models.Gameplay.Player;

/**
 * Created by brianotte on 2/12/18.
 */

public class Client extends Observable {

    private static Client single_instance = new Client();

    private HashMap<String, Game> gameMap;
    private Game activeGame;
    private boolean isLoggedIn;
    private boolean isRegistered;
    private String userName;
    private String password;
    private String authToken;
    private Request loginRequest;
    private Request registerRequest;
    private int commandNum;

    //constructor
    private Client(){
        this.commandNum = 0;
        this.gameMap = new HashMap<>();
        isLoggedIn = false;
        isRegistered = false;
        activeGame = null;
    }

    public static Client getInstance()
    {
        return single_instance;
    }

    public void setIsLoggedIn(boolean b)
    {
        isLoggedIn= b;
    }

    public void setUserName(String u)
    {
        userName = u;
    }

    public void setAuthToken(String a) {
        authToken=a;
        setChanged();
        notifyObservers(a);
    }

    public void setPassword(String p)
    {
        password=p;
    }

    public Game getActiveGame()
    {
        return activeGame;
    }

    public boolean getIsLoggedIn()
    {
        return isLoggedIn;
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

    public void setLoginRequest(Request l)
    {
        loginRequest=l;
    }

    public void sendMessage(String m) {
        setChanged();
        notifyObservers(m);
    }

    public Request getRegisterRequest() {
        return registerRequest;
    }

    public Request getLoginRequest() {
        return loginRequest;
    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
       // startGame();
    }

    public Game getGameById(String gameId) {
        return gameMap.get(gameId);
    }

    public ArrayList<Player> getGamePlayers(String gameId) {
        Game test = getGameById(gameId);
        test.getId();
        return getGameById(gameId).getPlayers();
    }

    public int getGameSize(String gameId) {
        return getGamePlayers(gameId).size();
    }

    public HashMap<String, Game> getGameMap() {
        return gameMap;
    }

    public void setGameMap(HashMap<String, Game> gameMap) {
            this.gameMap = gameMap;
    }

    public ArrayList<Game> getGameList(){
        ArrayList<Game> gameList = new ArrayList<>();
        for(Game g : gameMap.values())
        {
            gameList.add(g);
        }
//        for(String i: this.gameMap.keySet()){
//
//            returnList.add(this.gameMap.get(i));
//        }
        return gameList;
    }


    public void addPlayerToGame(String gameId, String username)
    {
        gameMap.get(gameId).addPlayer(new Player(username));
        joinGame();
    }

    public void removePlayerFromGame(String gameId, String username)
    {
        gameMap.get(gameId).getPlayers().remove(username);
        leaveGame();
    }

    //Can we just add this to createGame() instead?
    public void addGameToMap(String gameId, Game game) {
        gameMap.put(gameId, game);
        createGame();
    }

    //TODO: Can we pass parameters into these and add functionality? Or are these only to notify?
    //observables:
    public void createGame ()
    {
        setChanged();
        notifyObservers("create");
    }

    public void joinGame ()
    {
        setChanged();
        notifyObservers("join");
    }

    public void leaveGame ()
    {
        setChanged();
        notifyObservers("leave");
    }

    public void startGame ()
    {
        setChanged();
        notifyObservers("start");
    }

    public void setRegisterRequest(Request r){ registerRequest=r; }

    //This is for users that are not get officially login to the system
    public void clientLogin(Request lR)
    {
        loginRequest = lR;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    public void incCommandNum(int num) {
        commandNum += num;
    }
}