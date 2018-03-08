package game.Stats;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IStatesPresenter;
import Models.Result;
import Services.GUI.GameGuiFacade;
import game.Chat.ChatFragment;

/**
 * Created by fjameson on 2/28/18.
 */

public class StatsPresenter implements IStatesPresenter, Observer{

    public Context context;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    ChatFragment chatFragment = new ChatFragment();
    //Constructor:
    public StatsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //Functionality:
    //TODO - add route points to a player:
    public void addPointsToPlayer(){}

    //TODO - subtract trains:
    public void subtractPlayerTrains(){}

    //TODO - display player information
    public void displayInformation(){}

    //Navigation:

    public Result returnToGame(Context c)
    {
        return null;
    }

    public Result viewChat(Context c)
    {
        return null;
    }

    public Result viewGameHistory(Context c){
        return null;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}