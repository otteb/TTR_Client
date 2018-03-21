package Services.GUI;

import AsyncTasks.Lobby.CreateGameAsyncTask;
import AsyncTasks.Lobby.JoinGameAsyncTask;
import AsyncTasks.LoginAndRegister.LoginAsyncTask;
import AsyncTasks.LoginAndRegister.RegisterAsyncTask;
import AsyncTasks.Lobby.StartGameAsyncTask;
import Client_Server_Communication.ClientFacade;
import Models.*;
import Models.Gameplay.Game;
import ObserverPattern.TTR_Observable;

import java.util.Observer;

//this class takes information from the presenter, and packages it into a request object
    //that is then sent to the AsyncTasks:
public class GuiFacade {

    public void login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        new LoginAsyncTask().execute(loginRequest);
    }

    public void register (String username, String password)
    {
        Request registerRequest = new Request();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        new RegisterAsyncTask().execute(registerRequest);
    }
    public void createGame (String gameId)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(gameId);
        request.setCommandNum(Client.getInstance().getCommandNum());
        new CreateGameAsyncTask().execute(request);
    }

    public void joinGame (Game game, String player)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(game.getId());
        request.setUsername(player);
        request.setCommandNum(Client.getInstance().getCommandNum());
        new JoinGameAsyncTask().execute(request);
    }


    public void startGame (String gameId)
    {
        Request request = new Request();
        request.setGameId(gameId);
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setUsername(Client.getInstance().getUserName());
        request.setCommandNum(Client.getInstance().getCommandNum());
        new StartGameAsyncTask().execute(request);
    }

    public void addObserver(Observer o)
    {
        TTR_Observable.getInstance().addObserver(o);
    }

}
