package game;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Interfaces.IGamePresenter;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Route;
import Models.Result;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import StatePattern.MyTurn;
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

    public void switchToCards(Context c)
    {
        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToTrainCards();
    }

    public void switchToDestCards(Context c)
    {
        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToDestCards();
    }

    public void switchToGameHistory(Context c)
    {
        context = c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToGameHistory();
    }


    public void claimRoute(Context c, int routeNumber)
    {
        context =c;
        Client.getInstance().getCurState().claimRoute(routeNumber);

//        Client.getInstance().getCurState().claimRoute(this);
//        //to implement the state pattern, do this in MyTurn?
//
//        //draw line -- new color for that player
//
//        //increment routes
//        Route rt = new Route();
//        ActiveGame.getInstance().getMyPlayer().getClaimedRoutes().add(rt);
//
//        //updateStats player points
//        ActiveGame.getInstance().getMyPlayer().addPoints(7);
//
//        //updateStats trains left
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

        gui.endTurn();
//        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
//        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
    }

    public void claimOtherRoute(Context c)
    {
        context=c;
        //draw line -- new color for that player

        //increment routes
//        Route rt = new Route();
//        ActiveGame.getInstance().getActivePlayerObj().getClaimedRoutes().add(rt);
//
//        //updateStats player points
//        ActiveGame.getInstance().getActivePlayerObj().addPoints(4);
//
//        //updateStats trains left
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
        gui.endTurn();
//        String username = ActiveGame.getInstance().getActivePlayerObj().getName();
//        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        TTR_Observable.getInstance().updateStats("stats");
    }

    public int selectingRoute(float x, float y)
    {
        int bestRoute= -1;
        Map<Integer, Route> routes= ActiveGame.getInstance().getRoutes();
        for(Integer routeNumber: routes.keySet()) {
            float m = (float) ((routes.get(routeNumber).getStartCity().getYPosition() - routes.get(routeNumber).getEndCity().getYPosition()) /
                    (routes.get(routeNumber).getStartCity().getXPosition()- routes.get(routeNumber).getEndCity().getXPosition()));
            float b = (float) (routes.get(routeNumber).getStartCity().getYPosition() - (m * routes.get(routeNumber).getStartCity().getXPosition()));
            float testY = m * x + b;
            if (testY >= y - 40 && testY <= y + 40) {
                    bestRoute = routeNumber;
            }
        }
        return  bestRoute;
    }


    // We need this for updating the claimed routes
    @Override
    public void update(Observable observable, Object o) {
    }
}