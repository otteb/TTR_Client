package Services.Commands;

import Client_Server_Communication.GamePlayFacade;
import Interfaces.IGamePlay;
import Models.Request;
import Models.Result;

public class GamePlayServices implements IGamePlay {
    private static GamePlayServices theOne = new GamePlayServices();

    public static GamePlayServices getInstance() {
        return theOne;
    }


    private GamePlayServices() {}
    //reference the services in the server:
    //update the Client and Game models with all of the information returned
    //from the Server - the Presenter will decide what information to display:

    //TODO
    @Override
    public void setupGame(Request request) {
        System.out.println("COMMAND EXECUTING - SetUpGame");
    }

    //TODO
    @Override
    public void addGameHistory(Request request) {
        System.out.println("COMMAND EXECUTING - addGameHistory");
    }

    //TODO
    @Override
    public void discardDestCards(Request request) {
        System.out.println("COMMAND EXECUTING - discardDestCards");
    }

    @Override
    public void updateClient(Request request) {
        System.out.println("COMMAND EXECUTING - updateClient");
    }
}
