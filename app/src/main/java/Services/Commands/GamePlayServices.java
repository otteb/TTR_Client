package Services.Commands;

import java.util.HashMap;
import java.util.Map;

import Interfaces.IGamePlay;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Route;
import Models.Gameplay.Score;
import Models.Request;
import ObserverPattern.TTR_Observable;
import StatePattern.GameSetup;
import StatePattern.MyTurn;
import StatePattern.NotMyTurn;

public class GamePlayServices implements IGamePlay {
    private static GamePlayServices theOne = new GamePlayServices();

    public static GamePlayServices getInstance() {
        return theOne;
    }


    private GamePlayServices() {}
    //reference the services in the server:
    //updateStats the Client and Game models with all of the information returned
    //from the Server - the Presenter will decide what information to display:

    @Override
    public void setupGame(Request request) {
        System.out.println("COMMAND EXECUTING - SetUpGame");
        if(request.getGameId().equals(Client.getInstance().getActiveGame().getId()))
        {
            //set the Players:
            ActiveGame.getInstance().setPlayers(request.getGame().getPlayers());
            //Game History
            ActiveGame.getInstance().setHistory(request.getGame().getHistory());
            //faceupCards:
            ActiveGame.getInstance().setFaceUpCards(request.getGame().getFaceUpCards());
            //routes:
            ActiveGame.getInstance().setMasterList(request.getGame().getRoutesMap());
            Client.getInstance().setCurState(new GameSetup());
            TTR_Observable.getInstance().updateStats("stats");
        }
    }

    //Testing Phase:
    @Override
    public void addGameHistory(Request request) {
        System.out.println("COMMAND EXECUTING - addGameHistory");
        //updateStats the active game's gameHistory:
        ActiveGame.getInstance().getHistory().add(request.getAction());
        TTR_Observable.getInstance().updateHistory();
    }

    //Testing Phase:
    @Override
    public void discardDestCards(Request request) {
        System.out.println("COMMAND EXECUTING - discardDestCards");
        ActiveGame.getInstance().getPlayer(request.getUsername()).discardDestCards(request.getDiscardDest());
        if(Client.getInstance().getUserName().equals(request.getUsername()))
        {
            //only update this for the user
            TTR_Observable.getInstance().updateStats("destinations");
        }
        TTR_Observable.getInstance().updateStats("stats");
//        if(ActiveGame.getInstance().getActivePlayer().equals(Client.getInstance().getUserName())){
//            Client.getInstance().setCurState(new MyTurn());
//        }
//        else
//        {
//            Client.getInstance().setCurState(new NotMyTurn());
//        }
    }

    @Override
    public void drawDestCards(Request request) {
        System.out.println("COMMAND EXECUTING - drawDestCards");
        ActiveGame.getInstance().getPlayer(request.getUsername()).addDrawnDestCards(request.getDestCards());
        if(request.getUsername().equals(Client.getInstance().getUserName()))
        {
            TTR_Observable.getInstance().updateCards("destinations");
        }
    }

    @Override
    public void drawTrainCard(Request request) {
        System.out.println("COMMAND EXECUTING - drawTrainCard");
        ActiveGame.getInstance().getPlayer(request.getUsername()).getHand().add(request.getTrainCards().get(0));
        if(request.getUsername().equals(Client.getInstance().getUserName()))
        {
            TTR_Observable.getInstance().updateCards("deck");
        }
        TTR_Observable.getInstance().updateStats("hand");
    }

    @Override
    public void takeFaceUpCard(Request request) {
        System.out.println("COMMAND EXECUTING - takeFaceUpCard");
        ActiveGame.getInstance().getPlayer(request.getUsername()).getHand().add(request.getTrainCards().get(0));
        ActiveGame.getInstance().replaceFaceUp(request.getCardIndex(), request.getTrainCards().get(1).getColor());
        TTR_Observable.getInstance().updateCards("faceUp");
    }

    //Doesn't do anything... just looks pretty:
    //Well it doesn't even look pretty...
    @Override
    public void updateClient(Request request) {
        System.out.println("COMMAND EXECUTING - updateClient");
    }

    //this function updates the player's endGame-specific information:
    @Override
    public void endGame(Request request) {
        System.out.println("COMMAND EXECUTING - endGame");
        //update the client's information AND/OR the ActiveGame:
        ActiveGame.getInstance().setPlayers(request.getGame().getPlayers()); //these players have all the latest scores:
        //Action that tells the client to change to the EndGameFragment:
        TTR_Observable.getInstance().endGame();
    }

    @Override
    public void endTurn(Request request) {
        System.out.println("COMMAND EXECUTING - endTurn");
        ActiveGame.getInstance().setActivePlayer(request.getUsername());
//        ActiveGame.getInstance().incTurn();
        TTR_Observable.getInstance().updateTurn();
        TTR_Observable.getInstance().updateStats("stats");
        //username here is the name of the active player whose turn it is
        if(request.getUsername().equals(Client.getInstance().getUserName()))
        {
            if(Client.getInstance().getCurState() instanceof GameSetup)
            {
                System.out.println("It's your turn but you have to discard a destination card first.");
            }

            if(Client.getInstance().getCurState() instanceof NotMyTurn)
            {
                System.out.println("It's your turn!");
                Client.getInstance().setCurState(new MyTurn());
            }
        }
        else
        {
            Client.getInstance().setCurState(new NotMyTurn());
        }
    }

    @Override
    public void shuffleFaceUp(Request request) {
        System.out.println("COMMAND EXECUTING - shufflefaceUp");
        ActiveGame.getInstance().setFaceUpCards(request.getTrainCards());
        TTR_Observable.getInstance().updateCards("faceUp");
    }

    @Override
    public void claimRoute(Request request){
        System.out.println("COMMAND EXECUTING - claimRoute");
        ActiveGame.getInstance().setActivePlayer(request.getUsername());
        Route routeToRemove = request.getRoute();

        Map<Integer, Route> temp= ActiveGame.getInstance().getRoutes();


        //add route to players claimed Routes

        ActiveGame.getInstance().getPlayer(request.getUsername()).getClaimedRoutes().put(request.getRoute().getRouteNumber(), routeToRemove);
        //updating the number of trains current player has
        int curNumTrains= ActiveGame.getInstance().getPlayer(request.getUsername()).getNumTrains();
        curNumTrains -= request.getRoute().getLength();
        ActiveGame.getInstance().getPlayer(request.getUsername()).setNumTrains(curNumTrains);
        ActiveGame.getInstance().getPlayer(request.getUsername()).removeTrainCards(request.getRoute());
        Score curPlayerScore = ActiveGame.getInstance().getPlayer(request.getUsername()).getScore();
        curPlayerScore.addRoutePoints(request.getRoute().getLength());
        ActiveGame.getInstance().getPlayer(request.getUsername()).setScore(curPlayerScore);
       // ActiveGame.getInstance().getPlayer(request.getUsername()).setScore(request.);

//        if(request.getUsername().equals(Client.getInstance().getUserName())){
//            //updating MyPlayer's stuff
//            ActiveGame.getInstance().getMyPlayer().setClaimedRoutes(ActiveGame.getInstance().getPlayer(request.getUsername()).getClaimedRoutes());
//            ActiveGame.getInstance().getMyPlayer().setNumTrains(curNumTrains);
//            ActiveGame.getInstance().getMyPlayer().removeTrainCards(request.getRoute());
//            ActiveGame.getInstance().getMyPlayer().setScore(ActiveGame.getInstance().getPlayer(request.getUsername()).getScore());
//        }
        //removing route from Active Game Routes and adding it to Claimed Routes
        temp.remove(request.getRoute().getRouteNumber());
        ActiveGame.getInstance().setRoutes(temp);

        ActiveGame.getInstance().getClaimedRoutes().put(request.getRoute().getRouteNumber(),routeToRemove);

        TTR_Observable.getInstance().claimRoute("claim");
        TTR_Observable.getInstance().updateStats("stats");
    }
}
