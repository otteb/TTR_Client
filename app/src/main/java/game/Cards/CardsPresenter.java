package game.Cards;

import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ICardsPresenter;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;
import game.Chat.ChatFragment;

/**
 * Created by brianotte on 3/7/18.
 */

public class CardsPresenter implements ICardsPresenter, Observer {
    //attributes:
    public Context context;
    MainActivity mainActivity;
    public GameGuiFacade gameGuiFacade = new GameGuiFacade();
    ChatFragment chatFragment = new ChatFragment();
    //Constructor:
    public CardsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    //functionality

    //TODO - Sends back an array of destination cards:
    public void sendBackDestinationCard(){

    }

    //TODO - Draw a train card:
    public void drawTrainCard(){

    }

    //Navigating Views:

    //TODO - switch view back to the stats fragment:
    public void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();

    }

    //TODO - switch back to the game fragment:

    //Observer:

    //TODO - I honestly have no idea:
    @Override
    public void update(Observable observable, Object o) {

    }
}
