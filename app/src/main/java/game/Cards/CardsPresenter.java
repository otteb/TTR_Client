package game.Cards;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ICardsPresenter;
import Services.GUI.GameGuiFacade;
import game.Chat.ChatFragment;

/**
 * Created by brianotte on 3/7/18.
 */

public class CardsPresenter implements ICardsPresenter, Observer {
    //attributes:
    public Context context;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    ChatFragment chatFragment = new ChatFragment();
    //Constructor:
    public CardsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //TODO - this needs to display the Game History actions as they trickle in:
    public void displayGameHistory(){

    }

    //TODO - switch view back to the stats fragment
    public void switchToStats(Context c){

    }

    //TODO - I honestly have no idea:
    @Override
    public void update(Observable observable, Object o) {

    }
}
