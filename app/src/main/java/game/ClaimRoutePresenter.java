package game;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;

/**
 * Created by ferrell3 on 3/26/18.
 */

public class ClaimRoutePresenter implements Observer{
    public Context context;
    private MainActivity mainActivity;
    private GameGuiFacade gameGuiFacade = new GameGuiFacade();

    public ClaimRoutePresenter(Context c){
        this.context = c;
//        gameGuiFacade.addObserver(this);
    }

    void claimRoute(Context c, int routeNum, String color, int numReg, int numWild){
        context=c;
        //TODO: finish this
        ArrayList<TrainCard> cards = new ArrayList<>();
        int num = ActiveGame.getInstance().getMyPlayer().getNumColorCards(color);
        if(num <= numReg)
        {
            for(int i = 0; i < numReg; i++)
            {
                cards.add(new TrainCard(color));
            }
        }
//        ArrayList<TrainCard> cards = ActiveGame.getInstance().getMyPlayer().discardTrainCards(color, numReg);
//        ArrayList<TrainCard> wilds = ActiveGame.getInstance().getMyPlayer().discardTrainCards("wild", numWild);
//        for(TrainCard w : wilds)
//        {
//            cards.add(w);
//        }
        num = ActiveGame.getInstance().getMyPlayer().getNumColorCards("wild");
        if(num <= numWild)
        {
            for(int i = 0; i < numWild; i++)
            {
                cards.add(new TrainCard("wild"));
            }
        }

        if(!cards.isEmpty())
        {
            Client.getInstance().getCurState().claimRoute(ActiveGame.getInstance().getRoutes().get(routeNum), cards);
        }
//        String str = "You have selected " + numReg + " " + color + " cards and " + numWild + " wild cards.";
//        Toast.makeText(c, str, Toast.LENGTH_SHORT).show();

    }

    void switchToGame(Context c){
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.openGame();
    }

    @Override
    public void update(Observable observable, Object o) {
        //TODO: finish this
    }
}
