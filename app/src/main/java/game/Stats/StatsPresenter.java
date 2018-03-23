package game.Stats;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IStatesPresenter;
import Models.Result;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;
import game.Chat.ChatFragment;


public class StatsPresenter implements IStatesPresenter, Observer{

    public Context context;
    MainActivity mainActivity;
    GameGuiFacade gameGuiFacade = new GameGuiFacade();
    //Constructor:
    StatsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //Navigation:

    void returnToGame(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

    void viewChat(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToChat();
    }

    void viewHistory(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToGameHistory();
    }

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