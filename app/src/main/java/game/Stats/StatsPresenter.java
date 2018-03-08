package game.Stats;

import android.content.Context;

import Interfaces.IStatesPresenter;
import Models.Result;

/**
 * Created by fjameson on 2/28/18.
 */

public class StatsPresenter implements IStatesPresenter{

    public Context context;
    public StatsPresenter(Context c)
    {
        context=c;
    }

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



}