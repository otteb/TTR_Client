package Services;

import java.util.ArrayList;

import Interfaces.ILobby;
import Models.Client;
import Models.Gameplay.Game;
import Models.Gameplay.Player;
import Models.Request;
import Models.Result;

public class LobbyServices implements ILobby {

    //this is going to be the client facade: THIS IS FOR TESTING

    private static LobbyServices theGS = new LobbyServices();

    public static LobbyServices getInstance() {
        return theGS;
    }

    private LobbyServices() {}

    //this one should be done
    @Override
    public void createGame(Request request){ //(String authToken, String gameId){
        //this is where the Client (in the models package) needs to be updated with the latest information.
        //return a game, gameId, and message;
        System.out.println("COMMAND EXECUTED - CREATE GAME");

        Game newGame = new Game(request.getGameId());
        //add players to the newGame:
//        ArrayList<Player> tempList = newGame.getPlayers();
//        tempList.add(new Player(request.getUsername()));
//        newGame.setPlayers(tempList);
        //add the game into the Client Model's HashMap:
        Client.getInstance().addGameToMap(request.getGameId(), newGame);
    }

    @Override
    public void joinGame(Request request) { //(String authToken, String gameId);
        System.out.println("COMMAND EXECUTING - JOIN GAME");
        Client.getInstance().addPlayerToGame(request.getGameId(), request.getUsername());
        Client.getInstance().setActiveGame(Client.getInstance().getGameMap().get(request.getGameId()));
    }

    @Override
    public void leaveGame(Request request) {
        System.out.println("COMMAND EXECUTING - LEAVE GAME");
        Client.getInstance().removePlayerFromGame(request.getGameId(), request.getUsername());
//        Client.getInstance().getGameMap().get(request.getGameId()).getPlayers().remove(request.getUsername());
    }

    @Override
    public void startGame(Request request) { //(String authToken, String gameId);

        System.out.println("COMMAND EXECUTED - START GAME");
        if(Client.getInstance().getActiveGame().getId().equals(request.getGameId()))
        {
            Client.getInstance().getGameMap().get(request.getGameId()).setActive(true);
            Client.getInstance().startGame();
            //PHASE2: Stop lobby poller, start game poller
        }
        else
        {
            Client.getInstance().getGameMap().get(request.getGameId()).setActive(true);
        }
    }

    @Override //polling response
    public void updateClient(Request request) { //(String authToken);
        System.out.println("COMMAND EXECUTED - UPDATE CLIENT GAME");
    }
}
