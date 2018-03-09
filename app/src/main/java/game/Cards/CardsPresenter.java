package game.Cards;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Interfaces.ICardsPresenter;
import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;
import Models.Gameplay.ActiveGame;
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
            ArrayList<DestinationCard> discard= new ArrayList<>();
            discard.add(ActiveGame.getInstance().getMyPlayer().getDestination_cards().get(card-1));
//            ActiveGame.getInstance().getMyPlayer().getDestination_cards().remove(discard.get(0));
            gameGuiFacade.discardDestinationCard(discard);
            switchToGame(context);
        }

    }

    //TODO - Draw a train card:
    public void drawTrainCardFromDeck(){


    }

    public void drawTrainCardFromTable(int cardIndex) {
        if(cardIndex == 0)
        {
            Toast.makeText(context, "You haven't selected enough cards", Toast.LENGTH_SHORT).show();
        }
        else {
            //add cards to player hand
            //replace the cards on the table
            ActiveGame.getInstance().getMyPlayer().getHand().add(ActiveGame.getInstance().getFaceUpCards().get(cardIndex-1));
            ActiveGame.getInstance().getFaceUpCards().remove(cardIndex-1);

            TrainCard newCard = new TrainCard("blue");
            ActiveGame.getInstance().getFaceUpCards().add(cardIndex-1, newCard);
        }

    }

    //Navigating Views:

    public void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();

    }



    //Observer:

    @Override
    public void update(Observable observable, Object o) {

    }
}
