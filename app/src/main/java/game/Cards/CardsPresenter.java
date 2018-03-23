package game.Cards;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ICardsPresenter;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;

public class CardsPresenter implements ICardsPresenter, Observer {
    //attributes:
    public Context context;
    private MainActivity mainActivity;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();
    private boolean keepAll;
    //Constructor:
    public CardsPresenter(Context c){
        this.context = c;
        gameGuiFacade.addObserver(this);
    }


    public void sendBackDestinationCard(Context context, int card){
        this.context=context;
        if (card == 0)
        {
            if(!keepAll)
            {
                Toast.makeText(context, "You haven't selected a card, press again to keep all three cards.", Toast.LENGTH_SHORT).show();
                keepAll = true;
            }
            else //keepAll is true, they pressed it a second time
            {
                Client.getInstance().getCurState().returnDestCard(this, -1);
                keepAll = false;
            }
        }
        else
        {
            keepAll = false;
            //draw from deck and append to player hand
            Client.getInstance().getCurState().returnDestCard(this, card-2);
//            switchToGame(context);
        }
    }


    public void drawTrainCardFromDeck(){
        Client.getInstance().getCurState().drawTrainCard(this);
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

    void drawDestinationCards() {
        Client.getInstance().getCurState().drawDestCards(this);
    }

    //Navigating Views:

    public void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

    void skipTurn(){
        gameGuiFacade.endTurn();
    }

    //Observer:

    @Override
    public void update(Observable observable, Object o) {
        if(o.equals("faceUp"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateFaceUp();
        }
        else if(o.equals("deck"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.displayDrawnCard();
        }
        else if(o.equals("destinations"))
        {
            mainActivity = (MainActivity) context;
            mainActivity.updateDestinations();
        }
//        else if(o.equals("turn"))
//        {
//            mainActivity = (MainActivity) context;
//            mainActivity.displayTurn();
////            String username = ActiveGame.getInstance().getActivePlayer();
////            Toast.makeText(getActivity(), "It's " + ActiveGame.getInstance().getActivePlayer() + "'s turn", Toast.LENGTH_SHORT).show();
//        }
        observable.hasChanged();
    }
}
