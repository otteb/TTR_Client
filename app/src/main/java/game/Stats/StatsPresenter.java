package game.Stats;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IStatesPresenter;
import Models.Gameplay.ActiveGame;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;


public class StatsPresenter implements IStatesPresenter, Observer{

    public Context context;
    MainActivity mainActivity;
    GameGuiFacade gameGuiFacade = new GameGuiFacade();
    //Constructor:
    StatsPresenter(Context c){
        this.context = c;
        TTR_Observable.getInstance().addObserver(this);
    }

    //Navigation:

    void returnToGame(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

//    void returnToClaimRoute(Context c)
//    {
//        context=c;
//        mainActivity= (MainActivity)context;
//        mainActivity.switchToClaimRoute(ActiveGame.getInstance().getMyPlayer().getSelectedRoute());
//    }


    void viewChat(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToChat();
    }

//    void viewHistory(Context c)
//    {
//        context=c;
//        mainActivity = (MainActivity) context;
//        mainActivity.switchToGameHistory();
//    }

    @Override
    public void update(Observable observable, Object o) {
        if(o.equals("stats"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateStats();
        }
        else if(o.equals("hand"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateHand();
        }
        observable.hasChanged();
    }
}