package Services.Commands;

import Interfaces.ILobby;
import Models.Client;
import Models.Gameplay.Game;
import Models.Request;

public class LobbyServices implements ILobby {
    private static LobbyServices theOne = new LobbyServices();

    public static LobbyServices getInstance() {
        return theOne;
    }

    private LobbyServices() {}

    //this one should be done
    @Override
    public void createGame(Request request){ //(String authToken, String gameId){
        //this is where the Client (in the models package) needs to be updated with the latest information.
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS CREATING GAME: " + request.getGameId());

        Game newGame = new Game(request.getGameId());
        Client.getInstance().addGameToMap(request.getGameId(), newGame);
    }

    @Override
    public void joinGame(Request request) {
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS JOINING GAME: " + request.getGameId());
        Client.getInstance().addPlayerToGame(request.getGameId(), request.getUsername());
        Client.getInstance().setActiveGame(Client.getInstance().getGameMap().get(request.getGameId()));
    }

    @Override
    public void leaveGame(Request request) {
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS LEAVING GAME " + request.getGameId());
        Client.getInstance().removePlayerFromGame(request.getGameId(), request.getUsername());
    }

    @Override
    public void startGame(Request request) { //(String authToken, String gameId);

        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS STARTING GAME: " + request.getGameId());
        if(Client.getInstance().getActiveGame().getId().equals(request.getGameId()))
        {
            Client.getInstance().getGameMap().get(request.getGameId()).setActive(true);
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
