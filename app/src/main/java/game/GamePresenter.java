package game;

import android.content.Context;
import android.widget.Toast;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Interfaces.IGamePresenter;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Route;
import ObserverPattern.TTR_Observable;
import activities.MainActivity;


public class GamePresenter implements IGamePresenter, Observer {
    private Context context;
    private MainActivity mainActivity;

    public GamePresenter(Context c) {
        context = c;
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
    }



    public Route selectingRoute(Context c, float x, float y)
    {
        context =c;
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
        if(bestRoute != null && bestRoute.isDoubleRoute()) {
            Route claimedRoute = findClaimedDoubleRoute(bestRoute.getStartCity());
            String curPlayer = ActiveGame.getInstance().getMyPlayer().getName();
            if (claimedRoute != null && claimedRoute.getOwner().equals(curPlayer)) {
                Toast.makeText(context, "You can't claim adjacent double routes", Toast.LENGTH_SHORT).show();
                return null;
            }
            else {
                return bestRoute;
            }
        }
        return bestRoute;
    }

    public Route findClaimedDoubleRoute(String startCity)
    {
        Map<Integer, Route> routes= ActiveGame.getInstance().getClaimedRoutes();
        for(Integer routeNumber: routes.keySet()){
            if(routes.get(routeNumber).getStartCity().equals(startCity))
            {
                return routes.get(routeNumber);
            }
        }
        return null;
    }



    // We need this for updating the claimed routes
    @Override
    public void update(Observable observable, Object o) {
        if(context != null) {
            if (o.equals("claim")) {
                mainActivity = (MainActivity) context;
                mainActivity.updateRoutes();
                observable.hasChanged();
            } else if (o.equals("end")) {
                mainActivity = (MainActivity) context;
                mainActivity.switchToEndGame();
                observable.hasChanged();
            }
        }
    }
}