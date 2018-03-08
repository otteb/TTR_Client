package Services.Commands;

import Interfaces.IGamePlay;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Request;

public class GamePlayServices implements IGamePlay {
    private static GamePlayServices theOne = new GamePlayServices();

    public static GamePlayServices getInstance() {
        return theOne;
    }


    private GamePlayServices() {}
    //reference the services in the server:
    //update the Client and Game models with all of the information returned
    //from the Server - the Presenter will decide what information to display:

    //TODO - change this to update the ActiveGameSingleton instead of the Client.Model.ActiveGame:
    @Override
    public void setupGame(Request request) {
        System.out.println("COMMAND EXECUTING - SetUpGame");
        //set the Players:
        ActiveGame.getInstance().setPlayers(request.getGame().getPlayers());
        //Game History
        ActiveGame.getInstance().setHistory(request.getGame().getHistory());
        //faceupCards:
        ActiveGame.getInstance().setFaceUpCards(request.getGame().getFaceUpCards());
    }

    //Testing Phase:
    @Override
    public void addGameHistory(Request request) {
        System.out.println("COMMAND EXECUTING - addGameHistory");
        //update the active game's gameHistory:
        ActiveGame.getInstance().getHistory().add(request.getAction());
    }

    //Testing Phase:
    @Override
    public void discardDestCards(Request request) {
        System.out.println("COMMAND EXECUTING - discardDestCards");
        ActiveGame.getInstance().getPlayer(request.getUsername()).discardDestCards(request.getDiscardDest());
    }


    //this doesn't need to be done until phase III
    @Override
    public void drawTrainCards(Request request) {
        System.out.println("COMMAND EXECUTING - drawTrainCards");
    }


    //Doesn't do anything... just looks pretty:
    @Override
    public void updateClient(Request request) {
        System.out.println("COMMAND EXECUTING - updateClient");
    }
}
