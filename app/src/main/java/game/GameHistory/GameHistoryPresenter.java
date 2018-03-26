package game.GameHistory;

import android.app.Activity;
import android.content.Context;

import java.util.Observable;
import java.util.Observer;
import Interfaces.IGameHistoryPresenter;
import ObserverPattern.TTR_Observable;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;


class GameHistoryPresenter implements IGameHistoryPresenter, Observer {
    public Context context;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();
    private MainActivity mainActivity;

    //Constructor:
    GameHistoryPresenter(Context c){
        this.context = c;
        TTR_Observable.getInstance().addObserver(this);
    }

    void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

    void switchToChat(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToChat();
    }


    @Override
    public void update(Observable observable, Object result) {
        if(result.equals("history"))
        {
            mainActivity = (MainActivity)((Activity)context);

            mainActivity.updateGameHistory();
        }
        observable.hasChanged();
    }
}
