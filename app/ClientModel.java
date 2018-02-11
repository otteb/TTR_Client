package cs340.ttr_client;

import java.util.ArrayList;
import java.util.List;

import com.example.shared.Request;
import com.example.shared.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjameson on 2/10/18.
 */

public class ClientModel {
    private static ClientModel single_instance=null;
    private List<Game> gameList;
    private Game activeGame;
    private Boolean isLoggedIn;
    private String userName;
    private String password;
    private String authToken;
    private Request loginRequest;

    public Result loginTest()
    {
        userName= "FINN";
        password = "FINES";
        authToken="something";
        Result result = new Result();
        if(loginRequest.getUsername().equals(userName) && loginRequest.getPassword().equals(password))
        {
            setIsLoggedIn(true);
            result.setAuthToken(authToken);

        }
        else {
            setIsLoggedIn(false);
            result.setErrorMsg("The Login Failed");
        }
        return result;
    }

    //This is for users that are not get officially login to the system
    public void clientLogin(Request lR)
    {
        loginRequest = lR;
    }


    ClientModel(){
    }

    public static ClientModel getInstance()
    {
        if (single_instance == null)
        {
            single_instance= new ClientModel();
        }
        return single_instance;
    }


    public void appendGameList(Game game)
    {
        if( gameList == null)
        {
            gameList = new ArrayList<Game>();
        }
        gameList.add(game);
    }

    public void setActiveGame(Game game)
    {
        activeGame= game;
    }

    public void setIsLoggedIn(Boolean b)
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

    public List<Game> getGameList()
    {
        return gameList;
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







}
