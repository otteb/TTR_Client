package Services;

import AsyncTasks.CreateGameAsyncTask;
import AsyncTasks.JoinGameAsyncTask;
import AsyncTasks.LoginAsyncTask;
import AsyncTasks.RegisterAsyncTask;
import AsyncTasks.StartGameAsyncTask;
import Client_Server_Communication.ClientFacade;
import Models.*;
import Models.Gameplay.Game;

import java.util.Observer;

/**
 * Created by fjameson on 2/9/18.
 */

public class GuiFacade {
    ClientFacade clientFacade = new ClientFacade();

    public void login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.execute(loginRequest);
    }

    public void register (String username, String password)
    {
        Request registerRequest = new Request();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask();
        registerAsyncTask.execute(registerRequest);
    }
    public void createGame (String gameId)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(gameId);
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
        CreateGameAsyncTask createGameAsyncTask = new CreateGameAsyncTask();
        createGameAsyncTask.execute(request);
    }

    public void joinGame (Game game, String player)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(game.getId());
        request.setUsername(player);
        JoinGameAsyncTask joinGameAsyncTask = new JoinGameAsyncTask();
        joinGameAsyncTask.execute(request);
    }


    public void startGame (String gameId)
    {
        Request request = new Request();
        request.setGameId(gameId);
        request.setAuthToken(Client.getInstance().getAuthToken());
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
        StartGameAsyncTask startGameAsyncTask = new StartGameAsyncTask();
        //executes the startGameAsyncTask:
        startGameAsyncTask.execute(request);
    }

    public void addObserver(Observer o)
    {
        Client.getInstance().addObserver(o);
    }

}
