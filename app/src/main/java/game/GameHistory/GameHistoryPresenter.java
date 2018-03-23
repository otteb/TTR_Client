package game.GameHistory;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;
import Interfaces.IGameHistoryPresenter;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;


public class GameHistoryPresenter implements IGameHistoryPresenter, Observer {
    public Context context;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();
    private MainActivity mainActivity;

    //Constructor:
    public GameHistoryPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    public void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

    public void switchToChat(Context c){
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
