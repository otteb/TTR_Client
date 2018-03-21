package game.Cards;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Interfaces.ICardsPresenter;
import Models.Cards.DestinationCard;
import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import StatePattern.State;
import activities.MainActivity;
import game.Chat.ChatFragment;


public class CardsPresenter implements ICardsPresenter, Observer {

    public Context context;
    private MainActivity mainActivity;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();
    //Constructor:
    public CardsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }

    public void sendBackDestinationCard(Context context, int card){
        this.context=context;
        if (card == 0)
        {
            Toast.makeText(context, "You haven't selected a card", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //draw from deck and append to player hand
            Client.getInstance().getCurState().returnDestCard(this, card-2);
            switchToGame(context);
        }
    }

    /*
   *
   *@post card=0 will ret an error Toast
   * card if != 0 will be passed on to the guiFacade, which shall remove it
   * */
    public void drawTrainCardFromDeck(){
        Client.getInstance().getCurState().drawTrainCard(this);
        //change to the next state
        //to drew1Card or to notmyturn
    }

    public void drawTrainCardFromTable(int cardIndex) {
        if(cardIndex == 0)
        {
            Toast.makeText(context, "You haven't selected enough cards", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Client.getInstance().getCurState().takeFaceUpCard(this, cardIndex-1);
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
        if(o.equals("faceUp"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateFaceUp();
        }
        observable.hasChanged();
    }
}
