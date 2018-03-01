package game;

import android.content.Context;

import Models.Result;

/**
 * Created by fjameson on 2/28/18.
 */

public class GamePresenter {
    Context context;
    public GamePresenter(Context c) {
        context=c;
        //guiFacade.addObserver(this);
    }
    public Result switchToLobby(Context c)
    {
        return null;
    }

    public Result switchToStats(Context c)
    {
        return null;
    }

    public Result claimRoute(Context c)
    {
        return null;
    }

}