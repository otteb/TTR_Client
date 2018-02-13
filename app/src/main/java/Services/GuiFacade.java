package Services;

import Client_Server_Communication.ClientFacade;
import Models.*;

import java.util.ArrayList;

/**
 * Created by fjameson on 2/9/18.
 */

public class GuiFacade {
    ClientFacade clientFacade = new ClientFacade();
//    public Client clientModel= new Client();

    public Result login (String username, String password)
    {
        Request loginRequest = new Request();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
//        clientModel.setLoginRequest(loginRequest);
//        return clientModel.loginTest();
        return clientFacade.login(loginRequest);
    }

    public Result register (String username, String password)
    {
        Request registerRequest = new Request();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
//        clientModel.setRegisterRequest(registerRequest);
//        return clientModel.registerTest();
        return clientFacade.register(registerRequest);
    }
    public ArrayList<Game> createGame (Game game)
    {
        Request request = new Request();
        request.setAuthToken(Client.getInstance().getAuthToken());
        request.setGameId(game.getId());
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
        //have no need for the result:
        Result result = clientFacade.joinGame(request);
        //loop through the client facade and create an arrayList of games:
        ArrayList<Game> games = new ArrayList<>();
        for(String i: Client.getInstance().getGameMap().keySet()){
            games.add(Client.getInstance().getGameMap().get(i));
        }
        return games;
    }
    public Game joinGame (Game game, String player)
    {
        Request request = new Request();
        request.setGameId(game.getId());
        request.setUsername(player);
        Result result = clientFacade.joinGame(request);
        Game joinGame = Client.getInstance().getGameMap().get(result.getGameId());
        return joinGame;
    }


    public void startGame (String gameId)
    {
        Request request = new Request();
        request.setGameId(gameId);
        request.setAuthToken(Client.getInstance().getAuthToken());
        int temp = Client.getInstance().getCommandNum();
        request.setCommandNum(temp);
        Result result = clientFacade.startGame(request);
    }

}
