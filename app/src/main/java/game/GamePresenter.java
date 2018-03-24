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
import Services.Commands.GamePlayServices;
import Services.GUI.GameGuiFacade;
import StatePattern.MyTurn;
import activities.MainActivity;

import static java.lang.Math.abs;


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


    public void claimRoute(Context c, Route routeNumber)
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

    public Route selectingRoute(float x, float y)
    {
        Route bestRoute= null;
        float bestY= 100;
        Map<Integer, Route> routes= ActiveGame.getInstance().getRoutes();
        for(Integer routeNumber: routes.keySet()) {
            float m =  (((float)routes.get(routeNumber).getStartY() - (float)routes.get(routeNumber).getEndY()) /
                    ((float)routes.get(routeNumber).getStartX()- (float)routes.get(routeNumber).getEndX()));
            float b = (float) (routes.get(routeNumber).getStartY() - (m * routes.get(routeNumber).getStartX()));
            float testY = m * x + b;
            if (testY >= y - 10 && testY <= y + 10) {
                float smallY = (float)routes.get(routeNumber).getEndY();
                float smallX = (float)routes.get(routeNumber).getEndX();
                float largeY = (float)routes.get(routeNumber).getStartY();
                float largeX = (float)routes.get(routeNumber).getStartX();
                if ((float)routes.get(routeNumber).getStartY()< (float)routes.get(routeNumber).getEndY())
                {
                    smallY = (float)routes.get(routeNumber).getStartY();
                    largeY = (float)routes.get(routeNumber).getEndY();
                }
                if ((float)routes.get(routeNumber).getStartX()< (float)routes.get(routeNumber).getEndX())
                {
                    smallX = (float)routes.get(routeNumber).getStartX();
                    largeX = (float)routes.get(routeNumber).getEndX();
                }
                if(abs(y-testY) < bestY && y> smallY && y < largeY && x > smallX && x < largeX)
                {
                    bestRoute = routes.get(routeNumber);
                    bestY=testY;
                }
            }
        }
        return  bestRoute;
    }


    // We need this for updating the claimed routes
    @Override
    public void update(Observable observable, Object o) {
        if(o.equals("claim")) {
            mainActivity = (MainActivity) context;
            mainActivity.updateRoutes();
        }
        else if(o.equals("end"))
        {
            mainActivity = (MainActivity) context;
//            mainActivity.switchToEndGame();
        }
    }
}