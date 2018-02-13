package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brianotte on 2/12/18.
 */

public class Client {
    private static Client single_instance = null;



    private HashMap<String, Game> gameMap = new HashMap<>();
    private Game activeGame = new Game();
    private Boolean isLoggedIn;
    private Boolean isRegistered;
    private String userName;
    private String password;
    private String authToken;
    private Request loginRequest;
    private Request registerRequest;
    private Integer commandNum;


    //constructor
    Client(){}

    public static Client getInstance()
    {
        if (single_instance == null)
        {
            single_instance= new Client();
        }
        return single_instance;
    }



    public void setIsLoggedIn(Boolean b)
    {
        isLoggedIn= b;
    }

    public void setIsRegistered(Boolean b)
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
    }

    public void setPassword(String p)
    {
        password=p;
    }


    public Game getActiveGame()
    {
        return activeGame;
    }

    public Boolean getIsLoggedIn()
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
        this.gameMap = gameMap;
    }


    public void setRegisterRequest(Request r){ registerRequest=r; }
    //This is for users that are not get officially login to the system
    public void clientLogin(Request lR)
    {
        loginRequest = lR;
    }


    public Integer getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(Integer commandNum) {
        this.commandNum = commandNum;
    }
}