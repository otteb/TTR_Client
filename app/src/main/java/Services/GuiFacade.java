package Services;

import AsyncTasks.CreateGameAsyncTask;
import AsyncTasks.JoinGameAsyncTask;
import AsyncTasks.LoginAsyncTask;
import AsyncTasks.RegisterAsyncTask;
import AsyncTasks.StartGameAsyncTask;
import Client_Server_Communication.ClientFacade;
import Models.*;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by fjameson on 2/9/18.
 */

public class GuiFacade {
    ClientFacade clientFacade = new ClientFacade();
//    public Client clientModel= new Client();

    //these methods all need to be fixed to void functions;

    public void login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
//        clientModel.setLoginRequest(loginRequest);
//        return clientModel.loginTest();
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.execute(loginRequest);
//        clientFacade.login(loginRequest);
    }

    public void register (String username, String password)
    {
        Request registerRequest = new Request();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
//        clientModel.setRegisterRequest(registerRequest);
//        return clientModel.registerTest();
        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask();
        registerAsyncTask.execute(registerRequest);
//        return clientFacade.register(registerRequest);
    }
    public void createGame (Game game)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(game.getId());
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
        //have no need for the result:
//        Result result = clientFacade.joinGame(request);
        //loop through the client facade and create an arrayList of games:

//        ArrayList<Game> games = new ArrayList<>();
//        for(String i: Client.getInstance().getGameMap().keySet()){
//            games.add(Client.getInstance().getGameMap().get(i));
//        }
        CreateGameAsyncTask createGameAsyncTask = new CreateGameAsyncTask();
        createGameAsyncTask.execute(request);

    }
    public void joinGame (Game game, String player)
    {
        Request request = new Request();
        request.setGameId(game.getId());
        request.setUsername(player);
        Result result = clientFacade.joinGame(request);
        Game joinGame = Client.getInstance().getGameMap().get(result.getGameId());
        JoinGameAsyncTask joinGameAsyncTask = new JoinGameAsyncTask();
        joinGameAsyncTask.execute(request);
//        return joinGame;
    }


    public void startGame (String gameId)
    {
        Request request = new Request();
        request.setGameId(gameId);
        request.setAuthToken(Client.getInstance().getAuthToken());
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
//        Result result = clientFacade.startGame(request);
        StartGameAsyncTask startGameAsyncTask = new StartGameAsyncTask();
        //executes the startGameAsyncTask:
        startGameAsyncTask.execute(request);
    }

    public void addObserver(Observer o)
    {
        Client.getInstance().addObserver(o);
    }

}
