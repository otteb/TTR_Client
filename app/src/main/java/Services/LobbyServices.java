package Services;

import java.util.ArrayList;

import Interfaces.ILobby;
import Models.Client;
import Models.Gameplay.Game;
import Models.Request;
import Models.Result;

public class LobbyServices implements ILobby {

    //this is going to be the client facade: THIS IS FOR TESTING

    private static LobbyServices theGS = new LobbyServices();

    public static LobbyServices getInstance() {
        return theGS;
    }

    private LobbyServices() {}

    //TODO: Finish implementing ILobby and adding functionality to methods

    //TODO: Remember to add the authToken to each request in order to skip it while adding commands

    //this one should be done
    @Override
    public Result createGame(Request request){ //(String authToken, String gameId){
        //this is where the Client (in the models package) needs to be updated with the latest information.
        //return a game, gameId, and message;
        System.out.println("COMMAND EXECUTED - CREATE GAME");

        Game newGame = new Game(request.getGameId());
        //add players to the newGame:
        ArrayList<String> tempList = newGame.getPlayers();
        tempList.add(request.getUsername());
        newGame.setPlayers(tempList);
        //add the game into the Client Model's HashMap:
        Client.getInstance().addGameToMap(request.getGameId(), newGame);
//        HashMap<String, Game> tempGameMap = Client.getInstance().getGameMap();
//        tempGameMap.put(request.getGameId(),newGame);
//        Client.getInstance().setGameMap(tempGameMap);

        //do we actually need to return anything??
        return null;
    }

    @Override
    public Result joinGame(Request request) { //(String authToken, String gameId);
        System.out.println("COMMAND EXECUTING - JOIN GAME");
//        Game currentGame = Client.getInstance().getGameMap().get(request.getGameId());
//        ArrayList<String> tempList = currentGame.getPlayers();
//        tempList.add(request.getUsername());
//        currentGame.setPlayers(tempList);
        //add the game into the Client Model's HashMap:
//        HashMap<String, Game> tempGameMap = Client.getInstance().getGameMap();
//        tempGameMap.put(request.getGameId(),currentGame);
//        Client.getInstance().setGameMap(tempGameMap);
        Client.getInstance().addPlayerToGame(request.getGameId(), request.getUsername());

        Client.getInstance().setActiveGame(Client.getInstance().getGameMap().get(request.getGameId()));
//        Client.getInstance().addGameToMap(request.getGameId(), currentGame);

        return null;
    }

    @Override
    public Result leaveGame(Request request) {
        System.out.println("COMMAND EXECUTING - LEAVE GAME");
        Client.getInstance().removePlayerFromGame(request.getGameId(), request.getUsername());
//        Client.getInstance().getGameMap().get(request.getGameId()).getPlayers().remove(request.getUsername());
        return null;
    }

    @Override
    public Result startGame(Request request) { //(String authToken, String gameId);

        System.out.println("COMMAND EXECUTED - START GAME");
        Game activeGame = new Game(request.getGameId());
        Client.getInstance().setActiveGame(activeGame);
        return null;
    }

    @Override //polling response
    public Result updateClient(Request request) { //(String authToken);
        System.out.println("COMMAND EXECUTED - UPDATE CLIENT GAME");
        return null;
    }

    //TODO:

}
