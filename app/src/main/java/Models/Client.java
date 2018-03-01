package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by brianotte on 2/12/18.
 */

public class Client extends Observable {
    private static Client single_instance = new Client();



    private HashMap<String, Game> gameMap;
    private Game activeGame = new Game();
    private boolean isLoggedIn;
    private boolean isRegistered;
    private String userName;
    private String password;
    private String authToken;
    private Request loginRequest;
    private Request registerRequest;
    private int commandNum;


    //constructor
    Client(){
        this.commandNum = 0;
        this.gameMap = new HashMap<>();
        isLoggedIn = false;
        isRegistered = false;
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

    public void setAuthToken(String a)
    {
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

    public void sendMessage(String m)
    {
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
    }
    public HashMap<String, Game> getGameMap() {
        return gameMap;
    }
    public void setGameMap(HashMap<String, Game> gameMap) {
        //if(gameMap !=) {
            this.gameMap = gameMap;
            createGame();
       // }

    }
    public ArrayList<Game> getGameList(){
        ArrayList<Game> returnList = new ArrayList<>();
        for(String i: this.gameMap.keySet()){

            returnList.add(this.gameMap.get(i));
        }
        return returnList;
    }

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