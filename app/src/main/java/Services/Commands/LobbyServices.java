package Services.Commands;

import Interfaces.ILobby;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Game;
import Models.Gameplay.Player;
import Models.Request;
import ObserverPattern.TTR_Observable;

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
        if (request.getUsername().equals(Client.getInstance().getUserName()))
        {
            Client.getInstance().setActiveGame(Client.getInstance().getGameById(request.getGameId()));
        }
    }

    @Override
    public void leaveGame(Request request) {
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS LEAVING GAME " + request.getGameId());
        Client.getInstance().removePlayerFromGame(request.getGameId(), request.getUsername());
    }

    @Override
    public void startGame(Request request) { //(String authToken, String gameId);
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS STARTING GAME: " + request.getGameId());
        //Check if the game starting is this user's game
        Game temp = Client.getInstance().getActiveGame();
        if(temp != null && Client.getInstance().getIsLoggedIn() && temp.getId().equals(request.getGameId()))
//        if(temp != null && temp.getId().equals(request.getGameId()))
        {
            Client.getInstance().getGameById(request.getGameId()).setActive(true);
            ActiveGame.getInstance().setId(Client.getInstance().getActiveGame().getId());
            Client.getInstance().getPoller().stopLobbyCommands();
            ActiveGame.getInstance().getPoller().runGamePlayCommands();
            TTR_Observable.getInstance().startGame();
        }
        else
        {
            Client.getInstance().getGameById(request.getGameId()).setActive(true);
        }
    }

    @Override
    public void rejoinGame(Request request) {
        System.out.println("COMMAND EXECUTING - " + request.getUsername() + " IS REJOINING GAME: " + request.getGameId());
        Game temp = Client.getInstance().getActiveGame();
        if(temp != null && Client.getInstance().getIsLoggedIn() && temp.getId().equals(request.getGameId()))
        {
            Game currentGame = request.getGame();
            Client.getInstance().getGameById(request.getGameId()).setActive(true);

            ActiveGame.getInstance().setId(Client.getInstance().getActiveGame().getId());
            ActiveGame.getInstance().setPlayers(currentGame.getPlayers());
            ActiveGame.getInstance().setChats(currentGame.getChats());
            ActiveGame.getInstance().setHistory(currentGame.getHistory());
            ActiveGame.getInstance().setFaceUpCards(currentGame.getFaceUpCards());
            ActiveGame.getInstance().setMasterList(currentGame.getRoutesMap());
            for(Player plyr : currentGame.getPlayers())
            {
                ActiveGame.getInstance().setClaimedRoutes(plyr.getClaimedRoutes());
            }


            Client.getInstance().getPoller().stopLobbyCommands();
            ActiveGame.getInstance().getPoller().runGamePlayCommands();
            TTR_Observable.getInstance().rejoinGame();
        }
        else
        {
            Client.getInstance().getGameById(request.getGameId()).setActive(true);
        }
    }

    @Override
    public void removeGame(Request request) {
        System.out.println("COMMAND EXECUTING - REMOVE GAME: " + request.getGameId());
        if(Client.getInstance().getActiveGame().getId().equals(request.getGameId()))
        {
            Client.getInstance().resetActiveGame();
        }
        Client.getInstance().removeGameFromMap(request.getGameId());
    }

    @Override //polling response
    public void updateClient(Request request) { //(String authToken);
        System.out.println("COMMAND EXECUTED - UPDATE CLIENT GAME");
    }
}
