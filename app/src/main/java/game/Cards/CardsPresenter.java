package game.Cards;

import android.content.Context;
import android.widget.Toast;

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
    public void sendBackDestinationCard(Context context, int card){
        this.context=context;
        if (card == 0)
        {
            Toast.makeText(context, "You haven't selected a card", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //draw from deck and append to player hand
        }

    }

    //TODO - Draw a train card:
    public void drawTrainCardFromDeck(){


    }

    public void drawTrainCardFromTable(int cardChoice1, int cardChoice2){
        if(cardChoice1 == 0 || cardChoice2 == 0)
        {
            Toast.makeText(context, "You haven't selected enough cards", Toast.LENGTH_SHORT).show();
        }
        else {
            //add cards to player hand
            //replace the cards on the table
        }

    }

    //Navigating Views:

    //TODO - switch view back to the stats_linear fragment:
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
