package game;

import android.content.Context;
import android.text.method.Touch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Models.Cards.TrainCard;
import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Route;
import Services.GUI.GameGuiFacade;
import activities.MainActivity;


public class ClaimRoutePresenter implements Observer{
    public Context context;
    private MainActivity mainActivity;

    public ClaimRoutePresenter(Context c){
        ActiveGame.getInstance().getMyPlayer().setIsinProcessofClaimingRoute(true);
        this.context = c;
    }

    boolean claimRoute(Context c, int routeNum, String color, int numReg, int numWild){
        context=c;
        ArrayList<TrainCard> cards = new ArrayList<>();
        int numRegHand = ActiveGame.getInstance().getMyPlayer().getNumColorCards(color);
        int numWildHand = ActiveGame.getInstance().getMyPlayer().getNumColorCards("wild");
        Route route = ActiveGame.getInstance().getRoutes().get(routeNum);
        if(!route.getColor().equals(color) && !route.getColor().equals("wild") && (numReg != 0))
        {
            //check if the route color matches the selected color or is wild
            //advise the user they cannot claim this route with the wrong color and return from this method
            Toast.makeText(context, "You cannot claim this route with the color you have selected.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if((numReg <= numRegHand) && (numWild <= numWildHand)
                && (route.getLength() <= ActiveGame.getInstance().getMyPlayer().getNumTrains()))
        {
            //add selected cards to list to send to the server to claim the route
            for(int i = 0; i < numReg; i++)
            {
                cards.add(new TrainCard(color));
            }
            for(int w = 0; w < numWild; w++)
            {
                cards.add(new TrainCard("wild"));
            }

            //check to make sure the user has selected enough/the correct cards for the selected route
            if(cards.size() < route.getLength())
            {
                Toast.makeText(context, "You have not selected enough cards to claim this route", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Client.getInstance().getCurState().claimRoute(ActiveGame.getInstance().getRoutes().get(routeNum), cards);
                Toast.makeText(context, "Route claimed!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        else
        {
            Toast.makeText(context, "You do not have enough cards or trains to claim this route", Toast.LENGTH_SHORT).show();
        }
        return false;
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
