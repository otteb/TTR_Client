package Services.Commands;

import Interfaces.IGamePlay;
import Models.Client;
import Models.Gameplay.ActiveGame;
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
    //update the Client and Game models with all of the information returned
    //from the Server - the Presenter will decide what information to display:

    @Override
    public void setupGame(Request request) {
        System.out.println("COMMAND EXECUTING - SetUpGame");
        //set the Players:
        ActiveGame.getInstance().setPlayers(request.getGame().getPlayers());
        //Game History
        ActiveGame.getInstance().setHistory(request.getGame().getHistory());
        //faceupCards:
        ActiveGame.getInstance().setFaceUpCards(request.getGame().getFaceUpCards());
        Client.getInstance().setCurState(new GameSetup());
        TTR_Observable.getInstance().updateStats("stats");
    }

    //Testing Phase:
    @Override
    public void addGameHistory(Request request) {
        System.out.println("COMMAND EXECUTING - addGameHistory");
        //update the active game's gameHistory:
        ActiveGame.getInstance().getHistory().add(request.getAction());
        TTR_Observable.getInstance().updateHistory();
    }

    //Testing Phase:
    @Override
    public void discardDestCards(Request request) {
        System.out.println("COMMAND EXECUTING - discardDestCards");
        ActiveGame.getInstance().getPlayer(request.getUsername()).discardDestCards(request.getDiscardDest());
        TTR_Observable.getInstance().updateStats("destinations");
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

    @Override
    public void incTurn(Request request) {
        System.out.println("COMMAND EXECUTING - incTurn");
        ActiveGame.getInstance().incTurn();
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
        //TODO: implement claimRoute()
    }
}
