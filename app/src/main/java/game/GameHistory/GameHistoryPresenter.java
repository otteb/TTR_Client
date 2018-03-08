package game.GameHistory;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.IGameHistoryPresenter;
import Models.Gameplay.GameHistory;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;
import game.Chat.ChatFragment;

/**
 * Created by brianotte on 3/7/18.
 */

public class GameHistoryPresenter implements IGameHistoryPresenter, Observer {
    public Context context;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
//    GameHistoryPresenter gameHistoryPresenter = new GameHistoryPresenter(context);
    //create instance of the GameHistory Fragment:
    MainActivity mainActivity;
    //Constructor:
    public GameHistoryPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //TODO
    public void switchToStats(Context c){
        context=c;
        Toast.makeText(c, "Switching to Stats", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToStats();
    }

    //TODO
    public void switchToChat(Context c){
        context=c;
        Toast.makeText(c, "Switching to Chat", Toast.LENGTH_SHORT).show();
        mainActivity = (MainActivity) context;
        mainActivity.switchToChat();
    }


    //TODO - honestly do not know hahaheheheAAAHHHHHHH!!!!!
    @Override
    public void update(Observable observable, Object result) {
        //TODO - Make sure the observable
        if(result.equals("updateHistory"))
        {
            mainActivity = (MainActivity)((Activity)context);

            mainActivity.updateGameHistory();
        }
        else
        {
            Toast.makeText(context, (CharSequence) result, Toast.LENGTH_SHORT).show();
        }
        observable.hasChanged();
    }
}
