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
import Services.GUI.GameGuiFacade;
import StatePattern.GameSetup;
import StatePattern.State;
import activities.MainActivity;


public class GamePresenter implements IGamePresenter, Observer {
    private Context context;
    private MainActivity mainActivity;
    private GameGuiFacade gui = new GameGuiFacade();
//    private Player player;

    public GamePresenter(Context c) {
        context = c;
//        player = ActiveGame.getInstance().getMyPlayer();
        gui.addObserver(this);
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
//        mainActivity.switchToCards(ActiveGame.getInstance().getMyPlayer().getName(), destinationCardSetup);
        mainActivity.switchToCards(destinationCardSetup);

    }


    public Result claimRoute(Context c)
    {

//        Client.getInstance().getCurState().claimRoute(this);
//        //to implement the state pattern, do this in MyTurn?
//
//        //draw line -- new color for that player
//
//        //increment routes
//        Route rt = new Route();
//        ActiveGame.getInstance().getMyPlayer().getClaimedRoutes().add(rt);
//
//        //update player points
//        ActiveGame.getInstance().getMyPlayer().addPoints(7);
//
//        //update trains left
//        ActiveGame.getInstance().getMyPlayer().decNumTrains(4);
//
//        ActiveGame.getInstance().getMyPlayer().getHand().clear();
//
//        //add game history
//        Request fakeReq = new Request();
//        String action = Client.getInstance().getUserName() + " claimed a route.";
//        fakeReq.setAction(action);
//        GamePlayServices.getInstance().addGameHistory(fakeReq);

        //increment turn
        gui.incTurn();
        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
        return null;
    }

    public Result claimOtherRoute(Context c)
    {
        //draw line -- new color for that player

        //increment routes
//        Route rt = new Route();
//        ActiveGame.getInstance().getActivePlayerObj().getClaimedRoutes().add(rt);
//
//        //update player points
//        ActiveGame.getInstance().getActivePlayerObj().addPoints(4);
//
//        //update trains left
//        ActiveGame.getInstance().getActivePlayerObj().decNumTrains(3);
//
//        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
//        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
//        ActiveGame.getInstance().getActivePlayerObj().getHand().remove(0);
//
//        //add game history
//        Request fakeReq = new Request();
//        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
//        String action = username + " claimed a route.";
//        fakeReq.setAction(action);
//        GamePlayServices.getInstance().addGameHistory(fakeReq);

        //increment turn
        gui.incTurn();
        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
        return null;
    }

    // We need this for updating the claimed routes
    @Override
    public void update(Observable observable, Object o) {

    }
}