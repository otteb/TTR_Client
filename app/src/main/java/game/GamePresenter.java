package game;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IGamePresenter;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import Models.Gameplay.Route;
import Models.Request;
import Models.Result;
import ObserverPattern.TTR_Observable;
import Services.Commands.GamePlayServices;
import StatePattern.DrewDestCards;
import StatePattern.GameSetup;
import StatePattern.MyTurn;
import StatePattern.NotMyTurn;
import StatePattern.State;
import activities.MainActivity;


public class GamePresenter implements IGamePresenter, Observer {
    private Context context;
    private MainActivity mainActivity;
    private Player player = ActiveGame.getInstance().getMyPlayer();
    private State curState;
    private State nextState = null;

    public GamePresenter(Context c) {
        context = c;
        curState = new GameSetup();
        //guiFacade.addObserver(this);
    }

    public GamePresenter(Context c, State state) {
        context = c;
        curState = state; //new GameSetup();
        //guiFacade.addObserver(this);
    }

    public void switchToStats(Context c)
    {
        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToStats();
    }

    public void switchToCards(Context c, Boolean destinationCardSetup)
    {
//        player = new Player();
//        player.setName("kip");

        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToCards(player.getName(), destinationCardSetup);
        //replace the boolean with the state?
//        mainActivity.switchToCards(player.getName(), curState);
    }


    public Result claimRoute(Context c)
    {
        curState.claimRoute(this);
        //to implement the state pattern, do this in MyTurn?

        //draw line -- new color for that player

        //increment routes
        Route rt = new Route();
        ActiveGame.getInstance().getMyPlayer().getClaimedRoutes().add(rt);

        //update player points
        ActiveGame.getInstance().getMyPlayer().addPoints(7);

        //update trains left
        ActiveGame.getInstance().getMyPlayer().decNumTrains(4);

        ActiveGame.getInstance().getMyPlayer().getHand().clear();

        //add game history
        Request fakeReq = new Request();
//        fakeReq.setGameId(ActiveGame.getInstance().getId());
        String action = Client.getInstance().getUserName() + " claimed a route.";
        fakeReq.setAction(action);
        GamePlayServices.getInstance().addGameHistory(fakeReq);

        //increment turn
        ActiveGame.getInstance().incTurn();
        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
        return null;
    }

    public Result claimOtherRoute(Context c)
    {
        //draw line -- new color for that player

        //increment routes
        Route rt = new Route();
        ActiveGame.getInstance().getActivePlayerObj().getClaimedRoutes().add(rt);

        //update player points
        ActiveGame.getInstance().getActivePlayerObj().addPoints(4);

        //update trains left
        ActiveGame.getInstance().getActivePlayerObj().decNumTrains(3);

        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
//        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(2);

        //add game history
        Request fakeReq = new Request();
        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
        String action = username + " claimed a route.";
        fakeReq.setAction(action);
        GamePlayServices.getInstance().addGameHistory(fakeReq);

        //increment turn
        ActiveGame.getInstance().incTurn();
        username = ActiveGame.getInstance().getActivePlayerObj().getName();
        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
        return null;
    }



    //TODO change the update for the GamePresenter
    //this
    @Override
    public void update(Observable observable, Object o) {
//        if(o.equals("MyTurn"))
//        {
//            if(curState instanceof NotMyTurn)
//            {
//                State next = new MyTurn();
//                setCurState(next);
//            }
//            else if(curState instanceof DrewDestCards)
//            {
//                nextState = new MyTurn();
//            }
//        }
//        if(o.equals(""))
//        {
//            mainActivity = (MainActivity) context;
//            mainActivity.changeState();
//        }
//        observable.hasChanged(); //what is this doing?
    }

    public State getCurState() {
        return curState;
    }

    public void setCurState(State curState) {
        this.curState = curState;
    }

    public void changeState(State next)
    {
        if(nextState == null)
        {
            curState = next;
        }
        else
        {
            curState = nextState;
        }
    }

}