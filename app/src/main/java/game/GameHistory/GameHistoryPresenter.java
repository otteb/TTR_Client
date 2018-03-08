package game.GameHistory;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IGameHistoryPresenter;
import Models.Gameplay.GameHistory;
import Services.GUI.GameGuiFacade;
import game.Chat.ChatFragment;

/**
 * Created by brianotte on 3/7/18.
 */

public class GameHistoryPresenter implements IGameHistoryPresenter, Observer {
    public Context context;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    GameHistoryPresenter gameHistoryPresenter = new GameHistoryPresenter(context);
    //Constructor:
    public GameHistoryPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //TODO
    public void displayGameHistory(){

    }

    //TODO
    public void switchToGame(){

    }

    //TODO
    public void switchToStats(){

    }

    //TODO - honestly have no idea:
    @Override
    public void update(Observable observable, Object o) {

    }
}
