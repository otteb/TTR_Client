package game;

import android.content.Context;
import android.support.v4.content.ContextCompat;

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
import StatePattern.NotMyTurn;
import activities.MainActivity;

import static java.lang.Math.abs;
import static java.lang.Math.max;


public class GamePresenter implements IGamePresenter, Observer {
    private Context context;
    private MainActivity mainActivity;
//    private Player player;

    public GamePresenter(Context c) {
        context = c;
//        player = ActiveGame.getInstance().getMyPlayer();
        TTR_Observable.getInstance().addObserver(this);
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


    public void claimRoute(Context c, Route route)
    {
        context =c;
        mainActivity = (MainActivity) context;
        ActiveGame.getInstance().getMyPlayer().setSelectedRoute(route);
        mainActivity.switchToClaimRoute(route);
//        Client.getInstance().getCurState().claimRoute(routeNumber);

    }



    public Route selectingRoute(float x, float y)
    {
        Route bestRoute= null;
        float bestY= 100;
        Map<Integer, Route> routes= ActiveGame.getInstance().getRoutes();
        for(Integer routeNumber: routes.keySet()) {
                float smallY = (float)routes.get(routeNumber).getEndY();
                float smallX = (float)routes.get(routeNumber).getEndX();
                float largeY = (float)routes.get(routeNumber).getStartY();
                float largeX = (float)routes.get(routeNumber).getStartX();

            float A = x - smallX;
            float B = y - smallY;
            float C = largeX - smallX;
            float D = largeY - smallY;
            float dot = A * C + B * D;
            float len_sq = C * C + D * D;
            float param = dot/len_sq;
            float xx, yy;

            if (param < 0) {
                xx = smallX;
                yy = smallY;
            }
            else if (param > 1) {
                xx = largeX;
                yy = largeY;
            }
            else {
                xx = smallX + param * C;
                yy = smallY + param * D;
            }
            float dx = x - xx;
            float dy = y - yy;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            if(distance <= 20 && distance< bestY)
            {
                bestRoute=routes.get(routeNumber);
                bestY= distance;
            }
        }
        return  bestRoute;
    }


    // We need this for updating the claimed routes
    @Override
    public void update(Observable observable, Object o) {
        if(context != null) {
            if (o.equals("claim")) {
                mainActivity = (MainActivity) context;
                mainActivity.updateRoutes();
//                Client.getInstance().setCurState(new NotMyTurn());
//                GameGuiFacade gui = new GameGuiFacade();
//                gui.endTurn();
                observable.hasChanged();
            } else if (o.equals("end")) {
                mainActivity = (MainActivity) context;
                mainActivity.switchToEndGame();
                observable.hasChanged();
            }
        }
    }
}